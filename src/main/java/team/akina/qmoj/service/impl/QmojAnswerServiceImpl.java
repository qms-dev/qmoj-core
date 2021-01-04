package team.akina.qmoj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.akina.qmoj.constants.PlatformEnums;
import team.akina.qmoj.entity.QmojQuestion;
import team.akina.qmoj.entity.QmojQuestionWithBLOBs;
import team.akina.qmoj.exception.DataNotFindException;
import team.akina.qmoj.exception.InvalidInputException;
import team.akina.qmoj.mapper.QmojQuestionMapper;
import team.akina.qmoj.param.QmojAnswerParam;
import team.akina.qmoj.service.QmojAnswerService;
import team.akina.qmoj.utils.FieldConvertUtil;
import team.akina.qmoj.utils.LeetCodeHelper;

import java.util.concurrent.TimeUnit;

@Service
public class QmojAnswerServiceImpl implements QmojAnswerService {

    @Autowired
    QmojQuestionMapper qmojQuestionMapper;

    @Autowired
    LeetCodeHelper leetCodeHelper;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 根据qomoj的题目id提交题解
     *
     * @param answer 题解实体
     * @return 提交结果
     */
    @Override
    public String submitAnswer(QmojAnswerParam answer) {
        QmojQuestionWithBLOBs questionDetail = qmojQuestionMapper.selectByPrimaryKey(answer.getId());
        if (questionDetail == null) {
            throw new DataNotFindException("没有找到对应题目", answer.getId());
        }

        String submissionId = "";

        if ((int) questionDetail.getPlatform() == PlatformEnums.LEETCODE.getCode()) {
            submissionId = leetCodeHelper.submitAnswerToLeetCode(answer, questionDetail.getQuestionSlug(),
                    questionDetail.getQuestionId(), true);
        }

        if (!submissionId.isEmpty()) {
            QmojQuestion questionBaseInfo = FieldConvertUtil.INSTANCE.baseQmojQuestion(questionDetail);
            // 保存提交记录到Redis，用于在获取结果时提供必要信息
            redisTemplate.opsForValue().set("submit:" + submissionId, questionBaseInfo, 600, TimeUnit.SECONDS);
            return submissionId;
        }

        throw new InvalidInputException("不支持的平台类型", answer);
    }
}
