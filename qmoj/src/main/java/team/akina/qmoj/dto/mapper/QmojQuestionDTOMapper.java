package team.akina.qmoj.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import team.akina.qmoj.dto.QmojQuestionDTO;
import team.akina.qmoj.entity.QmojQuestionWithBLOBs;

@Mapper
public interface QmojQuestionDTOMapper {
    QmojQuestionDTOMapper INSTANCE = Mappers.getMapper(QmojQuestionDTOMapper.class);


    /**
     * 将question实体转换为DTO实体
     *
     * @param question 题目详情实体
     * @return
     */
    @Mappings({@Mapping(target = "platform", expression = "java(team.akina.qmoj.constants.PlatformEnums.getName(question.getPlatform()))"),
            @Mapping(target = "languages", expression = "java(team.akina.qmoj.utils.FieldConvertUtil.convertJsonToIntegerList(question.getLangToValidPlayground()))"),
            @Mapping(target = "topicTags", expression = "java(team.akina.qmoj.utils.FieldConvertUtil.convertJsonToStringList(question.getQuestionTags()))")})
    QmojQuestionDTO questionToQuestionDTO(QmojQuestionWithBLOBs question);
}
