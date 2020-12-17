package team.akina.qmoj.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.akina.qmoj.constants.Constants;
import team.akina.qmoj.service.QmojQuestionService;
import team.akina.qmoj.utils.http.HttpRequestHelper;
import team.akina.qmoj.utils.http.HttpResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LeetCodeHelperTest {

    @Autowired
    HttpRequestHelper httpRequestHelper;

    @Autowired
    LeetCodeHelper leetCodeHelper;

    @Autowired
    CookieStore cookieStore;

    @Autowired
    QmojQuestionService qmojQuestionService;

    @Test
    void login() {
        assertTrue(leetCodeHelper.loginToLeetCode());
    }

    @Test
    void loginAndSummit() throws IOException {

        String url = "https://leetcode-cn.com/problems/two-sum/submit/";
        Map<String, String> headers = new HashMap<>();

        Answer answer = new Answer();

        // 设置请求参数
        answer.setJudge_type("large");
        answer.setLang("cpp");
        answer.setQuestion_id(1);
        answer.setTest_mode(false);
        answer.setTyped_code("print(hello,world)!");

        String postContent = JSON.toJSONString(answer);

        if (cookieStore.getCookies().size() < 3) {
            leetCodeHelper.loginToLeetCode();
        }

        String sessionId = "";
        String csrftoken = "";

        // 添加必要的Cookie
        for (Cookie cookie : cookieStore.getCookies()) {
            if (cookie.getName().equals("LEETCODE_SESSION")) {
                sessionId = cookie.getValue();
            }

            if (cookie.getName().equals("csrftoken")) {
                csrftoken = cookie.getValue();
            }
        }

        headers.put("Cookie", "LEETCODE_SESSION=" + sessionId + ";csrftoken=" + csrftoken + ";");
        headers.put("X-CSRFToken", csrftoken);
        headers.put("X-Requested-With", "XMLHttpRequest");

        headers.put("User-Agent", Constants.USER_AGENT);
        headers.put("origin", Constants.LEETCODE_URL);
        headers.put("Referer", "https://leetcode-cn.com/problems/two-sum");

        HttpResult result = httpRequestHelper.doJsonPost(url, postContent, headers);

        // 验证是否提交成功，在返回的body里会有这次的提交流水号
        assertEquals(result.getCode(), HttpStatus.SC_OK);
    }

    @Test
    void updateQuestions()
    {
        qmojQuestionService.updateQuestionsFromLeetCode();
    }
}
