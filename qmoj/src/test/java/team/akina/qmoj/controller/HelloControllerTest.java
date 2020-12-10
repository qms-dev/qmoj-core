package team.akina.qmoj.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.akina.qmoj.exception.InvalidInputException;
import team.akina.qmoj.utils.LeetCodeHelper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HelloControllerTest {
    @Autowired
    LeetCodeHelper leetCodeHelper;

    @Autowired
    HelloController helloController;

    /**
     * 测试输入错误内容时抛出异常
     */
    @Test()
    void inputErrorTest() {
        InvalidInputException thrown = assertThrows(
                InvalidInputException.class,() -> helloController.hello(""));

        assertEquals(thrown.getMessage(), "请输入正确的题目标识");
    }
}
