package team.akina.qmoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "team.akina.qmoj")
@MapperScan(basePackages = "team.akina.qmoj.mapper")
public class QmojApplication {

    public static void main(String[] args) {
        SpringApplication.run(QmojApplication.class, args);
    }

}
