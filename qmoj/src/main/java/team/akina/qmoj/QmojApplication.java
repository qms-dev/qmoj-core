package team.akina.qmoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import team.akina.qmoj.constants.Constants;
import team.akina.qmoj.service.QmojQuestionService;
import team.akina.qmoj.utils.LeetCodeHelper;

@SpringBootApplication(scanBasePackages = "team.akina.qmoj")
@MapperScan(basePackages = "team.akina.qmoj.mapper")
@EnableAsync
public class QmojApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QmojApplication.class, args);
    }

    @Autowired
    QmojQuestionService qmojQuestionService;

    /**
     * 使用特定参数(pull)启动程序时更新数据库中的题目
     * 比如使用maven的run命令并携带参数:pull
     * mvn spring-boot:run -Dspring-boot.run.arguments="pull"
     */
    @Override
    public void run(String... args) {
        if (args != null && args.length > 0) {
            if (args[0].toUpperCase().equals(Constants.PULL_COMMAND)) {
                System.out.println("开始爬取并更新题库");
                qmojQuestionService.updateQuestionsFromLeetCode();
                System.out.println("题库更新完成");
            }
        }
    }
}
