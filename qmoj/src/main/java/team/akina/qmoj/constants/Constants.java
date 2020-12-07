package team.akina.qmoj.constants;

public class Constants {

    // LeetCode的主网址
    public static final String LEETCODE_URL = "https://leetcode-cn.com/";

    // 登录时请求的url
    public static final String SIGN_IN_URL = "https://leetcode-cn.com/accounts/login/";

    // 请求时默认携带的User-Agent
    public static final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)";

    // 获取LeetCode题目内容时的查询字符串
    public static final String LEETCODE_PROBLEM_DETAIL = "{\"operationName\":\"questionData\",\"variables\":{\"titleSlug\":\"%s\"}," +
            "\"query\":\"query questionData($titleSlug: String!) {question(titleSlug: $titleSlug) " +
            "{title    content    translatedTitle    translatedContent    topicTags {name      slug      translatedName}}}\"}";

    // 获取LeetCode题目内容时的URL
    public static final String LEETCODE_PROBLEM_CONTENT_URL = "https://leetcode-cn.com/problems/%s/";

    //获取LeetCode所有题目列表的URL
    public static  final String LEETCODE_PROBLEMS_LIST_URL = "https://leetcode-cn.com/api/problems/all/";

    // LeetCode的graphql接口
    public static final String LEETCODE_GRAPHQL = "https://leetcode-cn.com/graphql";
}
