package team.akina.qmoj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.akina.qmoj.dto.QmojQuestionDTO;
import team.akina.qmoj.dto.mapper.QmojQuestionDTOMapper;
import team.akina.qmoj.entity.QmojQuestion;
import team.akina.qmoj.entity.QmojQuestionSummary;
import team.akina.qmoj.mapper.QmojQuestionMapper;
import team.akina.qmoj.mapper.QmojQuestionSummaryMapper;
import team.akina.qmoj.service.QmojQuestionService;
import team.akina.qmoj.utils.LeetCodeHelper;

import java.util.List;

@Service
public class QmojQuestionServiceImpl implements QmojQuestionService {

    @Autowired
    private LeetCodeHelper leetCodeHelper;

    @Autowired
    private QmojQuestionMapper qmojQuestionMapper;

    @Autowired
    private QmojQuestionSummaryMapper qmojQuestionSummaryMapper;

    /**
     * todo 这里获取到题目之后，需要添加当前数据库没有的题目
     */
    @Override
    public void updateQuestionsFromLeetCode() {
        List<QmojQuestion> questionListWithDetail = leetCodeHelper.getQuestionListWithDetail();

    }

    /**
     * 根据题目id获取题目内容
     *
     * @param id 题目id
     * @return
     */
    @Override
    public QmojQuestionDTO getQuestionById(long id) {
        return QmojQuestionDTOMapper.INSTANCE.questionToQuestionDTO(qmojQuestionMapper.selectByPrimaryKey(id));
    }

    /**
     * 获取所有题目摘要列表
     *
     * @return 所有题目摘要列表
     */
    @Override
    public List<QmojQuestionSummary> getQuestionSet() {
        return qmojQuestionSummaryMapper.getAll();
    }
}
