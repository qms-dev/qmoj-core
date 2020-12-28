package team.akina.qmoj.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import team.akina.qmoj.dto.QmojJudgementDTO;
import team.akina.qmoj.pojo.LeetCodeJudgementResult;

@Mapper
public interface QmojJudgementDTOMapper {
    QmojJudgementDTOMapper INSTANCE = Mappers.getMapper(QmojJudgementDTOMapper.class);


    /**
     * 将LeetCode判题结果实体转换为DTO实体
     *
     * @param judgementResult LeetCode判题详情实体
     * @return
     */
    @Mappings({@Mapping(source = "submission_id", target = "id"),
            @Mapping(target = "result", expression = "java(team.akina.qmoj.utils.FieldConvertUtil.convertJudgementResult(judgementResult.getStatus_code()))"),
            @Mapping(source = "status_runtime", target = "time"),
            @Mapping(source = "status_memory", target = "memory"),
            @Mapping(target = "language", expression = "java(team.akina.qmoj.constants.LanguageEnums.getCode(judgementResult.getLang()))"),
            @Mapping(source = "last_testcase", target = "lastTestCase"),
            @Mapping(source = "expected_output", target = "expectedOutput"),
            @Mapping(source = "total_correct", target = "totalCorrect"),
            @Mapping(source = "total_testcases", target = "totalTestCases"),
            @Mapping(target = "error", expression = "java(team.akina.qmoj.utils.FieldConvertUtil.getErrorInfoFromJudgementResult(judgementResult))"),
            @Mapping(target = "errorDetail", expression = "java(team.akina.qmoj.utils.FieldConvertUtil.getErrorDetailFromJudgementResult(judgementResult))")})
    QmojJudgementDTO judgementResultToJudgementDTO(LeetCodeJudgementResult judgementResult);
}
