package team.akina.qmoj.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import team.akina.qmoj.constants.JudgementResultEnums;
import team.akina.qmoj.constants.LeetCodeJudgementStatusEnums;

@SpringBootTest
public class FieldConvertTest {

    @Test
    void convertJudgementResultTest() {
        Assert.isTrue(FieldConvertUtil.convertJudgementResult(LeetCodeJudgementStatusEnums.AC.getCode()) ==
                JudgementResultEnums.AC.getCode());

        Assert.isTrue(FieldConvertUtil.convertJudgementResult(LeetCodeJudgementStatusEnums.WA.getCode()) ==
                JudgementResultEnums.WA.getCode());

        Assert.isTrue(FieldConvertUtil.convertJudgementResult(LeetCodeJudgementStatusEnums.TLE.getCode()) ==
                JudgementResultEnums.TLE.getCode());

        Assert.isTrue(FieldConvertUtil.convertJudgementResult(LeetCodeJudgementStatusEnums.RE.getCode()) ==
                JudgementResultEnums.RE.getCode());

        Assert.isTrue(FieldConvertUtil.convertJudgementResult(LeetCodeJudgementStatusEnums.CE.getCode()) ==
                JudgementResultEnums.CE.getCode());

        Assert.isTrue(FieldConvertUtil.convertJudgementResult(100) ==
                JudgementResultEnums.Unknown.getCode());

    }
}
