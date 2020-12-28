package team.akina.qmoj.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.akina.qmoj.constants.Constants;
import team.akina.qmoj.constants.Response;
import team.akina.qmoj.dto.QmojJudgementDTO;
import team.akina.qmoj.dto.mapper.QmojJudgementDTOMapper;
import team.akina.qmoj.dto.mapper.QmojQuestionDTOMapper;
import team.akina.qmoj.entity.QmojQuestion;
import team.akina.qmoj.exception.DataNotFindException;
import team.akina.qmoj.exception.LeetCodeCrawlerException;
import team.akina.qmoj.pojo.LeetCodeJudgementResult;
import team.akina.qmoj.service.QmojJudgementService;
import team.akina.qmoj.utils.LeetCodeHelper;
import team.akina.qmoj.utils.http.HttpResult;

@Service
public class QmojJudgementServiceImpl implements QmojJudgementService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    LeetCodeHelper leetCodeHelper;

    @Override
    public QmojJudgementDTO queryJudgementResult(String submissionId) {
        QmojQuestion questionInfo = (QmojQuestion) redisTemplate.opsForValue().get("submit:" + submissionId);
        if (questionInfo == null) {
            throw new DataNotFindException("没有找到缓存的提交记录", submissionId);
        }

        HttpResult httpResult = leetCodeHelper.queryJudgementResult(submissionId, questionInfo.getQuestionSlug());

        LeetCodeCrawlerException leetCodeCrawlerException = new LeetCodeCrawlerException("没有找到判题信息，请重试", submissionId);

        if (httpResult.getCode() == HttpStatus.SC_OK) {
            LeetCodeJudgementResult leetCodeResult = JSON.parseObject(httpResult.getBody(), LeetCodeJudgementResult.class);
            switch (leetCodeResult.getState()) {
                case Constants.LEETCODE_STATE_STARTED:
                    leetCodeCrawlerException.setCode(Response.FAIL_PENDING);
                    leetCodeCrawlerException.setMsg("正在判题中，请稍后再试");
                    break;

                case Constants.LEETCODE_STATE_PENDING:
                    leetCodeCrawlerException.setMsg("无法查询该题号");
                    break;

                default:
                    return QmojJudgementDTOMapper.INSTANCE.judgementResultToJudgementDTO(leetCodeResult);
            }
        }

        throw leetCodeCrawlerException;
    }

}
