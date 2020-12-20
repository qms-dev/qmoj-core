package team.akina.qmoj.dto.mapper;

import com.alibaba.fastjson.JSON;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import team.akina.qmoj.constants.Enums;
import team.akina.qmoj.dto.QmojQuestionDto;
import team.akina.qmoj.entity.QmojQuestionWithBLOBs;

import java.util.List;

@Mapper
public interface QmojQuestionDtoMapper {
    QmojQuestionDtoMapper INSTANCE = Mappers.getMapper(QmojQuestionDtoMapper.class);


    /**
     * 将question实体转换为DTO实体
     * @param question 题目详情实体
     * @return
     */
    @Mappings({@Mapping(target = "platform", expression = "java(platFormConvert(question.getPlatform()))"),
            @Mapping(target = "languages", expression = "java(languagesConvert(question.getLangToValidPlayground()))"),
            @Mapping(target = "topic_tags", expression = "java(tagsConvert(question.getQuestionTags()))")})
    QmojQuestionDto questionToQuestionDto(QmojQuestionWithBLOBs question);


    /**
     * 转换平台类型
     *
     * @param code 平台类型编码
     * @return
     */
    default String platFormConvert(byte code) {
        return Enums.Platform.getName(code);
    }

    /**
     * 转换语言列表
     *
     * @param languages
     * @return
     */
    default List<Integer> languagesConvert(String languages) {
        return JSON.parseArray(languages, Integer.class);
    }

    /**
     * 转换标签列表
     *
     * @param tags
     * @return
     */
    default List<String> tagsConvert(String tags) {
        return JSON.parseArray(tags, String.class);
    }
}
