package team.akina.qmoj.service;

import team.akina.qmoj.dto.QmojQuestionDTO;

public interface QmojQuestionService {

    /**
     * 从LeetCode爬取数据并插入到数据库
     */
    void updateQuestionsFromLeetCode();

    /**
     * 通过题目id获取题目内容
     * @param id 题目id
     * @return
     */
    QmojQuestionDTO getQuestionById(long id);
}
