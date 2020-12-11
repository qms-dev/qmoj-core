package team.akina.qmoj.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.CookieStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import team.akina.qmoj.constants.Constants;
import team.akina.qmoj.entity.QmojQuestion;
import team.akina.qmoj.pojo.QmojStatStatusPairs;
import team.akina.qmoj.exception.LeetCodeCrawlerException;
import team.akina.qmoj.utils.http.HttpRequestHelper;
import team.akina.qmoj.utils.http.HttpResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LeetCodeHelper {
    @Value("${leetCode.loginName}")
    private String leetCodeLoginName;

    @Value("${leetCode.passWord}")
    private String leetCodePassword;

    @Autowired
    private CookieStore httpCookieStore;

    @Autowired
    private HttpRequestHelper httpRequestHelper;

    /**
     * 使用配置好的账号密码登录到LeetCode
     *
     * @return 登录结果
     */
    public boolean loginToLeetCode() {
        try {
            Map<String, Object> formContent = new HashMap();
            formContent.put("login", leetCodeLoginName);
            formContent.put("password", leetCodePassword);

            Map<String, String> headers = getDefaultHeader();
            headers.put("Connection", "keep-alive");
            headers.put("Referer", Constants.SIGN_IN_URL);

            httpRequestHelper.doPost(Constants.SIGN_IN_URL, formContent, headers);

            // 请求过后，若当前Cookies数量大于等于3个，则登陆成功
            return httpCookieStore.getCookies().size() >= 3;
        } catch (IOException ex) {
            throw new LeetCodeCrawlerException("登陆LeetCode失败", ex);
        }
    }

    /**
     * 根据题目Slug获取题目内容
     *
     * @param questionSlug 题目的Slug，对LeetCode来说就是每道题的url的最后一部分
     * @return LeetCode返回的题目内容
     */
    public String getQuestionDetailBySlug(String questionSlug) {
        try {
            Map<String, String> headers = getDefaultHeader();
            headers.put("Content-Type", "application/json");
            headers.put("Referer", String.format(Constants.LEETCODE_PROBLEM_CONTENT_URL, "questionSlug"));

            String postBody = String.format(Constants.LEETCODE_PROBLEM_DETAIL, questionSlug);

            HttpResult result = httpRequestHelper.doJsonPost(Constants.LEETCODE_GRAPHQL, postBody, headers);

            return StringUtil.decodeUnicode(result.getBody());

        } catch (IOException ex) {
            throw new LeetCodeCrawlerException("获取题目详情发生错误", ex, questionSlug);
        }
    }

    //返回LeetCode题目列表
    public List<QmojStatStatusPairs> getQuestionsList() {
        try {
            HttpResult result = httpRequestHelper.doGet(Constants.LEETCODE_PROBLEMS_LIST_URL);
            JSONObject jsonObject = JSON.parseObject(result.getBody());
            JSONArray jsonArray = jsonObject.getJSONArray("stat_status_pairs");
            List<QmojStatStatusPairs> list = new ArrayList<QmojStatStatusPairs>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject tempJSON = JSON.parseObject(jsonArray.getJSONObject(i).get("stat").toString());
                JSONObject tempJSON2 = JSON.parseObject(jsonArray.getJSONObject(i).get("difficulty").toString());
                QmojStatStatusPairs tempQmojStatStatusPairs = new QmojStatStatusPairs();
                tempQmojStatStatusPairs.setLevel(Integer.parseInt(tempJSON2.getString("level")));
                tempQmojStatStatusPairs.setQuestion__title_slug(tempJSON.getString("question__title_slug"));
                tempQmojStatStatusPairs.setQuestion_id(Long.parseLong(tempJSON.getString("question_id")));
                list.add(tempQmojStatStatusPairs);
            }
            return list;
        } catch (IOException ex) {
            throw new LeetCodeCrawlerException("获取LeetCode题目发生错误", ex);
        }
    }

    /**
     * todo 从LeetCode获取带详情的题目列表
     */
    public List<QmojQuestion> getQuestionListWithDetail() {
        throw new NotImplementedException();
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
