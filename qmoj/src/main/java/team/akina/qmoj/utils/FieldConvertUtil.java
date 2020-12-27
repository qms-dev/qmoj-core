package team.akina.qmoj.utils;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import team.akina.qmoj.constants.JudgementResultEnums;
import team.akina.qmoj.constants.LeetCodeJudgementStatusEnums;
import team.akina.qmoj.entity.QmojQuestion;
import team.akina.qmoj.entity.QmojQuestionWithBLOBs;
import team.akina.qmoj.pojo.LeetCodeJudgementResult;

import java.util.List;

@Mapper
public interface FieldConvertUtil {

    team.akina.qmoj.utils.FieldConvertUtil INSTANCE = Mappers.getMapper(team.akina.qmoj.utils.FieldConvertUtil.class);

    /**
     * 将QmojQuestionWithBLOBs实体完全转换成其父类，简化其属性
     *
     * @param question 题目详情实体
     * @return 简化之后的题目详情
     */
    @Mappings({})
    QmojQuestion baseQmojQuestion(QmojQuestionWithBLOBs question);

    /**
     * 转换语言列表
     *
     * @param languages
     * @return
     */
    public static List<Integer> convertJsonToIntegerList(String languages) {
        return JSON.parseArray(languages, Integer.class);
    }

    /**
     * 转换标签列表
     *
     * @param tags
     * @return
     */
    public static List<String> convertJsonToStringList(String tags) {
        return JSON.parseArray(tags, String.class);
    }

    /**
     * 将LeetCode返回的判题结果编码转换成amoj的
     */
    public static int convertJudgementResult(int judgementResult) {
        String resultName = LeetCodeJudgementStatusEnums.getName(judgementResult);

        for (JudgementResultEnums result : JudgementResultEnums.values()) {
            if (result.getName().equals(resultName)) {
                return result.getCode();
            }
        }

        return JudgementResultEnums.Unknown.getCode();
    }

    /**
     * 从LeetCode返回的判题结果中获取错误信息，目前只有RE和CE会出现错误信息
     */
    public static String getErrorInfoFromJudgementResult(LeetCodeJudgementResult leetCodeJudgementResult) {
        if (leetCodeJudgementResult.getStatus_code() == LeetCodeJudgementStatusEnums.RE.getCode()) {
            return leetCodeJudgementResult.getRuntime_error();
        }

        if (leetCodeJudgementResult.getStatus_code() == LeetCodeJudgementStatusEnums.CE.getCode()) {
            return leetCodeJudgementResult.getCompile_error();
        }

        return "";
    }

    /**
     * 从LeetCode返回的判题结果中获取错误详细信息
     */
    public static String getErrorDetailFromJudgementResult(LeetCodeJudgementResult leetCodeJudgementResult) {
        if (leetCodeJudgementResult.getStatus_code() == LeetCodeJudgementStatusEnums.RE.getCode()) {
            return leetCodeJudgementResult.getFull_runtime_error();
        }

        if (leetCodeJudgementResult.getStatus_code() == LeetCodeJudgementStatusEnums.CE.getCode()) {
            return leetCodeJudgementResult.getFull_compile_error();
        }

        return "";
    }
}
