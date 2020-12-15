package team.akina.qmoj;

import lombok.var;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.akina.qmoj.entity.QmojStatStatusPairs;
import team.akina.qmoj.utils.LeetCodeHelper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        var list = leetCodeHelper.getProblemsList();
        try{
            for(int i = 0 ;i<list.size();i++)
            {
                QmojStatStatusPairs zz = list.get(i);
                logger.info("第"+i+"个键值对的值为"+zz.getLevel()+zz.getQuestion__title_slug()+zz.getQuestion_id());
            }
        }
        catch (Exception e)
        {
            logger.info("报错！错误信息为："+e.toString());
        }

//        List<String> warnings = new ArrayList<String>();
//        boolean overwrite = true;
//        File configFile = new File("./src/main/resources/generatorConfig.xml");//直接放在文件下面，与pom.xml同级
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = cp.parseConfiguration(configFile);
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        myBatisGenerator.generate(null);
    }

}
