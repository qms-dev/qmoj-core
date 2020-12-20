package team.akina.qmoj.dto.mapper;

import com.alibaba.fastjson.JSON;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import team.akina.qmoj.dto.QmojQuestionDTO;
import team.akina.qmoj.entity.QmojQuestionWithBLOBs;

import java.util.List;

@Mapper
public interface QmojQuestionDTOMapper {
    QmojQuestionDTOMapper INSTANCE = Mappers.getMapper(QmojQuestionDTOMapper.class);


    /**
     * 将question实体转换为DTO实体
     * @param question 题目详情实体
     * @return
     */
    @Mappings({@Mapping(target = "platform", expression = "java(team.akina.qmoj.constants.PlatformNums.getName(question.getPlatform()))"),
            @Mapping(target = "languages", expression = "java(languagesConvert(question.getLangToValidPlayground()))"),
            @Mapping(target = "topicTags", expression = "java(tagsConvert(question.getQuestionTags()))")})
    QmojQuestionDTO questionToQuestionDTO(QmojQuestionWithBLOBs question);


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
