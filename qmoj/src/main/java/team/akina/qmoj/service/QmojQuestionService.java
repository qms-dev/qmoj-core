package team.akina.qmoj.service;

public interface QmojQuestionService {

    /**
     * 从LeetCode爬取数据并插入到数据库
     */
    void updateQuestionsFromLeetCode();
}
