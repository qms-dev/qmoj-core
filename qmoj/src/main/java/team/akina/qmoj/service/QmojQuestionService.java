package team.akina.qmoj.service;

import team.akina.qmoj.exception.LeetCodeCrawlerException;

public interface QmojQuestionService {

    /**
     * 从LeetCode爬取数据并插入到数据库
     */
    void updateQuestionsFromLeetCode() throws LeetCodeCrawlerException;
}
