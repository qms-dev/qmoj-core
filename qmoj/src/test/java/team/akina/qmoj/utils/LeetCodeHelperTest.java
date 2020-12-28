package team.akina.qmoj.utils;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.akina.qmoj.constants.Constants;
import team.akina.qmoj.service.QmojQuestionService;
import org.springframework.util.Assert;
import team.akina.qmoj.constants.LanguageEnums;
import team.akina.qmoj.entity.QmojQuestionWithBLOBs;
import team.akina.qmoj.mapper.QmojQuestionMapper;
import team.akina.qmoj.pojo.LeetCodeJudgementResult;
import team.akina.qmoj.param.QmojAnswerParam;
import team.akina.qmoj.utils.http.HttpRequestHelper;
import team.akina.qmoj.utils.http.HttpResult;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LeetCodeHelperTest {

    @Autowired
    HttpRequestHelper httpRequestHelper;

    @Autowired
    LeetCodeHelper leetCodeHelper;

    @Autowired
    QmojQuestionMapper qmojQuestionMapper;

    @Autowired
    QmojQuestionService qmojQuestionService;

    @Test
    void login() {
        assertTrue(leetCodeHelper.loginToLeetCode());
    }

    @Test
    void loginAndSummit() {
        QmojQuestionWithBLOBs question = qmojQuestionMapper.selectByPrimaryKey(1L);
        QmojAnswerParam answer = new QmojAnswerParam();
        answer.setAnswer("print(\"hello, world\")");
        answer.setLanguage(LanguageEnums.PYTHON3.getCode());

        String submissionId = leetCodeHelper.submitAnswerToLeetCode(answer, question.getQuestionSlug(), question.getQuestionId(), true);
        Assert.hasLength(submissionId);
    }

    /**
     * 查询判题结果，LeetCode对于这个check接口有调用限制，目前猜测是只能查询最近几次题目的判题结果，否则会返回pending，对我们暂无影响
     * 因此后续如果还要使用当前的查询代码，应该要更换submissionId
     */
    @Test
    void queryJudgementResult() {
        // Accepted
        HttpResult result = leetCodeHelper.queryJudgementResult("134005557", "two-sum");
        LeetCodeJudgementResult leetCodeResult = JSON.parseObject(result.getBody(), LeetCodeJudgementResult.class);
        Assert.isTrue(leetCodeResult.getStatus_code() == 10);

        // Runtime Error
        result = leetCodeHelper.queryJudgementResult("134010774", "two-sum");
        leetCodeResult = JSON.parseObject(result.getBody(), LeetCodeJudgementResult.class);
        Assert.isTrue(leetCodeResult.getStatus_code() == 15);

        // Compile Error
        result = leetCodeHelper.queryJudgementResult("134009164", "two-sum");
        leetCodeResult = JSON.parseObject(result.getBody(), LeetCodeJudgementResult.class);
        Assert.isTrue(leetCodeResult.getStatus_code() == 20);

        // Time Limit Exceeded
        result = leetCodeHelper.queryJudgementResult("134009574", "two-sum");
        leetCodeResult = JSON.parseObject(result.getBody(), LeetCodeJudgementResult.class);
        Assert.isTrue(leetCodeResult.getStatus_code() == 14);
    }

    @Test
    void updateQuestions()
    {
        qmojQuestionService.updateQuestionsFromLeetCode();
    }
}
