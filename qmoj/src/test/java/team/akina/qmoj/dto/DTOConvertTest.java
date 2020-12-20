package team.akina.qmoj.dto;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import team.akina.qmoj.dto.mapper.QmojQuestionDTOMapper;
import team.akina.qmoj.entity.QmojQuestionWithBLOBs;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DTOConvertTest {

    /**
     * 测试转换结果
     */
    @Test
    void convertToQuestionsDto() {
        QmojQuestionWithBLOBs question = new QmojQuestionWithBLOBs();
        question.setId(1L);
        question.setCreateTime(getCurrentTime());
        question.setModifyTime(null);
        question.setDifficulty((byte) 1);
        question.setPlatform((byte) 1);
        question.setContent("<p>给定一个整数数组 <code>nums</code>&nbsp;和一个目标值");
        question.setLangToValidPlayground(null);
        question.setQuestionSlug("two-sum");
        question.setTitle("两数之和");
        question.setQuestionTags("[\"数组\",\"哈希表\"]");

        QmojQuestionDTO questionDto = QmojQuestionDTOMapper.INSTANCE.questionToQuestionDTO(question);

        assertEquals(question.getContent(), questionDto.getContent());
        assertEquals(question.getQuestionTags(), JSON.toJSONString(questionDto.getTopicTags()));
    }

    /**
     * 获取当前时间
     */
    public Date getCurrentTime() {
        return new Date();
    }

}
