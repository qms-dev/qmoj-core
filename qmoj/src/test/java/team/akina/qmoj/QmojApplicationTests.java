package team.akina.qmoj;

import lombok.var;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.akina.qmoj.pojo.QmojStatStatusPairs;
import team.akina.qmoj.pojo.QmojTitleAndContent;
import team.akina.qmoj.utils.LeetCodeHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@SpringBootTest
class QmojApplicationTests {

    /**
     * 在这里测试自动生成实体类和dao以及对应的xml
     *
     * @throws IOException
     * @throws XMLParserException
     * @throws InvalidConfigurationException
     * @throws SQLException
     * @throws InterruptedException
     */
    @Autowired
    LeetCodeHelper leetCodeHelper;

    @Test
    void contextLoads() throws Exception {
        Logger logger = Logger.getLogger("test");
        var list = leetCodeHelper.getTitleAndContentList();

        for (int i = 0; i < list.size(); i++) {
            QmojTitleAndContent qmojTitleAndContent = list.get(i);
            logger.info("中文内容为:"+qmojTitleAndContent.getContent()+"中文标题为："+qmojTitleAndContent.getTitle()+"标签数组为："+qmojTitleAndContent.getTopicTags());
        }
    }

}
