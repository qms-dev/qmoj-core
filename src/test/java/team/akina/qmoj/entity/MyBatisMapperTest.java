package team.akina.qmoj.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import team.akina.qmoj.mapper.QmojQuestionSummaryMapper;

import java.util.List;

@SpringBootTest
public class MyBatisMapperTest {

    @Autowired
    QmojQuestionSummaryMapper qmojQuestionSetMapper;

    @Test
    void testQmojSetMapper()
    {
        List<QmojQuestionSummary> questionSet = qmojQuestionSetMapper.getAll();
        Assert.notNull(questionSet);
    }
}
