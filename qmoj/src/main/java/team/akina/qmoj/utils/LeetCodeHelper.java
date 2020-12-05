package team.akina.qmoj.utils;

import org.apache.http.client.CookieStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team.akina.qmoj.constants.Constants;
import team.akina.qmoj.utils.http.HttpRequestHelper;
import team.akina.qmoj.utils.http.HttpResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class LeetCodeHelper {
    @Value("${leetCode.loginName}")
    private String leetCodeLoginName;

    @Value("${leetCode.passWord}")
    private String leetCodePassword;

    private CookieStore httpCookieStore;

    private HttpRequestHelper httpRequestHelper;

    @Autowired
    public LeetCodeHelper(CookieStore httpCookieStore, HttpRequestHelper httpRequestHelper) {
        this.httpCookieStore = httpCookieStore;
        this.httpRequestHelper = httpRequestHelper;
    }

    /**
     * 使用配置好的账号密码登录到LeetCode
     *
     * @return 登录结果
     */
    public boolean loginToLeetCode() throws Exception {

        Map<String, Object> formContent = new HashMap();
        formContent.put("login", leetCodeLoginName);
        formContent.put("password", leetCodePassword);

        Map<String, String> headers = getDefaultHeader();
        headers.put("Connection", "keep-alive");
        headers.put("Referer", Constants.SIGN_IN_URL);

        httpRequestHelper.doPost(Constants.SIGN_IN_URL, formContent, headers);
        return httpCookieStore.getCookies().size() >= 3;
    }

    /**
     * 根据题目Slug获取题目内容
     *
     * @param questionSlug 题目的Slug，对LeetCode来说就是每道题的url的最后一部分
     * @return LeetCode返回的题目内容
     */
    public String getProblemDetailBySlug(String questionSlug) throws Exception {
        Map<String, String> headers = getDefaultHeader();
        headers.put("Content-Type", "application/json");
        headers.put("Referer", String.format(Constants.LEETCODE_PROBLEM_URL, "questionSlug"));

        String postBody = String.format(Constants.LEETCODE_PROBLEM_DETAIL, questionSlug);

        HttpResult result = httpRequestHelper.doJsonPost(Constants.LEETCODE_GRAPHQL, postBody, headers);
        if (result != null) {
            return StringUtil.decodeUnicode(result.getBody());
        }

        return null;
    }


    /**
     * 获取每次请求LeetCode都需要的默认Header
     *
     * @return 含有必填项的headers
     */
    private Map<String, String> getDefaultHeader() {
        Map<String, String> headers = new HashMap();
        headers.put("User-Agent", Constants.USER_AGENT);
        headers.put("origin", Constants.LEETCODE_URL);
        return headers;
    }
}
