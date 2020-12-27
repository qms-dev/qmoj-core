package team.akina.qmoj.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import team.akina.qmoj.constants.Constants;
import team.akina.qmoj.constants.LanguageEnums;
import team.akina.qmoj.entity.QmojQuestion;
import team.akina.qmoj.exception.InvalidInputException;
import team.akina.qmoj.exception.LeetCodeCrawlerException;
import team.akina.qmoj.pojo.*;
import team.akina.qmoj.utils.http.HttpRequestHelper;
import team.akina.qmoj.utils.http.HttpResult;

import java.io.IOException;
import java.net.URISyntaxException;
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

    @Autowired
    CookieStore cookieStore;

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
            headers.put("Referer", String.format(Constants.LEETCODE_PROBLEM_CONTENT_URL, questionSlug));

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
            HttpResult result = httpRequestHelper.doGet(Constants.LEETCODE_PROBLEMS_LIST_URL, null);
            JSONObject jsonObject = JSON.parseObject(result.getBody());
            JSONArray jsonArray = jsonObject.getJSONArray("stat_status_pairs");
            List<QmojStatStatusPairs> list = new ArrayList<QmojStatStatusPairs>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject statJSON = JSON.parseObject(jsonArray.getJSONObject(i).get("stat").toString());

                JSONObject difficultyJSON = JSON.parseObject(jsonArray.getJSONObject(i).get("difficulty").toString());

                QmojStatStatusPairs tempQmojStatStatusPairs = new QmojStatStatusPairs();


                tempQmojStatStatusPairs.setLevel(Integer.parseInt(difficultyJSON.getString("level")));

                tempQmojStatStatusPairs.setQuestion__title_slug(statJSON.getString("question__title_slug"));

                tempQmojStatStatusPairs.setQuestion_id(Long.parseLong(statJSON.getString("question_id")));

                list.add(tempQmojStatStatusPairs);
            }
            return list;
        } catch (IOException ex) {
            throw new LeetCodeCrawlerException("获取LeetCode题目发生错误", ex);
        }
    }

    //根据题目列表的slug请求对应的题目内容，然后再对返回结果进行解析。
    public List<QmojTitleAndContent> getTitleAndContentList() throws Exception {
        List<QmojStatStatusPairs> qmojStatStatusPairs = getQuestionsList();
        List<QmojTitleAndContent> qmojTitleAndContents = new ArrayList<QmojTitleAndContent>();
        for (int i = 0; i < qmojStatStatusPairs.size(); i++) {
            String responseJson = getQuestionDetailBySlug(qmojStatStatusPairs.get(i).getQuestion__title_slug());

            JSONObject dataJson = JSON.parseObject(JSON.parseObject(responseJson).get("data").toString());

            JSONObject questionJson = JSON.parseObject(dataJson.get("question").toString());

            QmojTitleAndContent qmojTitleAndContent = new QmojTitleAndContent();

            if (questionJson.get("translatedTitle") != null) {
                qmojTitleAndContent.setTitle(questionJson.get("translatedTitle").toString());
            } else if (questionJson.get("title") != null) {
                qmojTitleAndContent.setTitle(questionJson.get("title").toString());
            } else {
                qmojTitleAndContent.setTitle("");
            }

            if (questionJson.get("translatedContent") != null) {
                qmojTitleAndContent.setContent(questionJson.get("translatedContent").toString());
            } else if (questionJson.get("content") != null) {
                qmojTitleAndContent.setContent(questionJson.get("content").toString());
            } else {
                qmojTitleAndContent.setContent("");
            }


            JSONArray topicTags = questionJson.getJSONArray("topicTags");
            List<QmojTopicTag> qmojTopicTags = new ArrayList<QmojTopicTag>();
            for (int j = 0; j < topicTags.size(); j++) {
                QmojTopicTag qmojTopicTag = new QmojTopicTag();
                qmojTopicTag.setTopictag_name(topicTags.getJSONObject(j).get("name").toString());
                qmojTopicTag.setTopictag_slug(topicTags.getJSONObject(j).get("slug").toString());
                if (topicTags.getJSONObject(j).get("translatedName") != null) {
                    qmojTopicTag.setTopictag_translatedName(topicTags.getJSONObject(j).get("translatedName").toString());
                } else {
                    qmojTopicTag.setTopictag_translatedName("");
                }
                qmojTopicTags.add(qmojTopicTag);
            }
            qmojTitleAndContent.setTopicTags(qmojTopicTags);

            qmojTitleAndContents.add(qmojTitleAndContent);
        }
        return qmojTitleAndContents;
    }

    /**
     * todo 从LeetCode获取带详情的题目列表
     */
    public List<QmojQuestion> getQuestionListWithDetail() {
        throw new NotImplementedException();
    }

    /**
     * 提交题解到LeetCode，并且返回流水号
     *
     * @param answer       题解实体
     * @param questionSlug LeetCode题目Slug
     * @param retry        是否允许重试(第一次提交可能会因为cookie无效等原因，需要刷新cookie，此时如果获取题目失败允许重试一次)
     * @param questionId   LeetCode平台的题目id
     * @return LeetCode的题目提交流水号
     */
    public String submitAnswerToLeetCode(QmojAnswer answer, String questionSlug, int questionId, boolean retry) {
        // 提交题目时使用的url
        String url = String.format(Constants.LEETCODE_SUBMIT_URL, questionSlug);

        // 设置请求参数，judge_type,test_mode参数使用的是默认值
        LeetCodeAnswer leetCodeAnswer = new LeetCodeAnswer();
        leetCodeAnswer.setJudge_type("large");
        leetCodeAnswer.setLang(LanguageEnums.getName(answer.getLanguage()));
        leetCodeAnswer.setQuestion_id(questionId);
        leetCodeAnswer.setTest_mode(false);
        leetCodeAnswer.setTyped_code(answer.getAnswer());
        String postContent = JSON.toJSONString(leetCodeAnswer);

        // 设置请求头
        Map<String, String> headers = getHeaderWithLoginInfo(String.format(Constants.LEETCODE_PROBLEM_CONTENT_URL, questionSlug));

        HttpResult result = null;

        try {
            result = httpRequestHelper.doJsonPost(url, postContent, headers);
        } catch (IOException ex) {
            throw new LeetCodeCrawlerException("提交题解时请求LeetCode发生错误", ex, answer, questionSlug, questionId, retry);
        }

        switch (result.getCode()) {
            case HttpStatus.SC_OK:
                Map submitResult = JSON.parseObject(result.getBody(), Map.class);
                for (Object item : submitResult.entrySet()) {
                    Map.Entry<String, Object> entry = (Map.Entry) item;
                    // 返回结果中获取到的提交流水号
                    if (entry.getKey().equals("submission_id")) {
                        return entry.getValue().toString();
                    }
                }
                break;
            case Constants.STATUS_TOO_MANY_REQUESTS:
                // 触发了LeetCode的请求频率限制，Sleep一秒，重新请求
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new InvalidInputException("线程被中断");
                }
                retry = true;
                break;
        }

        if (retry) {
            return submitAnswerToLeetCode(answer, questionSlug, questionId, false);
        }

        throw new LeetCodeCrawlerException("提交题解时请求LeetCode发生错误", answer, questionSlug,
                questionId, result.getBody());
    }


    public HttpResult queryJudgementResult(String submissionId, String questionSlug) {
        String url = String.format(Constants.LEETCODE_QUERY_SUBMISSION, submissionId);
        String refererUrl = String.format(Constants.LEETCODE_PROBLEM_CONTENT_URL, questionSlug);

        Map<String, String> headers = getHeaderWithLoginInfo(refererUrl);

        HttpResult httpResult = null;

        try {
            httpResult = httpRequestHelper.doGet(url, null, headers);
        } catch (IOException | URISyntaxException ex) {
            throw new LeetCodeCrawlerException("查询LeetCode判题结果时发生错误", ex, submissionId, questionSlug);
        }

        return httpResult;
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

    /**
     * 为登录状态才能使用的请求构建完整Header
     *
     * @param refererUrl 需要加在header中的Referer，用于标记来源页面
     * @return 含有完整登录信息的headers
     */
    private Map<String, String> getHeaderWithLoginInfo(String refererUrl) {
        // 如果Cookie的数量还没达到三个，说明从来没有登录过，这里需要先进行登录
        if (cookieStore.getCookies().size() < 3) {
            loginToLeetCode();
        }

        LeetCodeLoginInfo loginInfo = getLoginInfo();

        // 设置请求头
        Map<String, String> headers = getDefaultHeader();

        headers.put("Cookie", "LEETCODE_SESSION=" + loginInfo.getSessionId() + ";csrftoken=" + loginInfo.getCsrfToken() + ";");
        headers.put("X-CSRFToken", loginInfo.getCsrfToken());
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Referer", refererUrl);

        return headers;
    }

    /**
     * 获取登录信息
     *
     * @return 登录信息实体
     */
    private LeetCodeLoginInfo getLoginInfo() {
        LeetCodeLoginInfo loginInfo = new LeetCodeLoginInfo();
        String sessionId = "";
        String csrfToken = "";

        // 添加必要的Cookie
        for (Cookie cookie : cookieStore.getCookies()) {
            if (cookie.getName().equals("LEETCODE_SESSION")) {
                sessionId = cookie.getValue();
            }

            if (cookie.getName().equals("csrftoken")) {
                csrfToken = cookie.getValue();
            }
        }

        loginInfo.setSessionId(sessionId);
        loginInfo.setCsrfToken(csrfToken);

        return loginInfo;
    }
}
