package team.akina.qmoj.service;

import team.akina.qmoj.exception.LeetCodeCrawlerException;
import team.akina.qmoj.dto.QmojQuestionDTO;
import team.akina.qmoj.entity.QmojQuestionSummary;
import java.util.List;
public interface QmojQuestionService {

    /**
     * 从LeetCode爬取数据并插入到数据库
     */
    void updateQuestionsFromLeetCode() throws LeetCodeCrawlerException;


    /**
     * 通过题目id获取题目内容
     * @param id 题目id
     * @return
     */
    QmojQuestionDTO getQuestionById(long id);

    /**
     * 获取所有题目摘要列表
     *
     * @return 所有题目摘要列表
     */
    List<QmojQuestionSummary> getQuestionSet();
}
