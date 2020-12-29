package team.akina.qmoj.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomExceptionTest {

    @Test
    void makeExceptionWithSpecifiedStatus() {
        InvalidInputException invalidInputException = new InvalidInputException("输入参数错误").withSpecifiedStatus(500);
        assertEquals(invalidInputException.getCode(), 500);
    }
}
