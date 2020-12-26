package team.akina.qmoj.utils;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 测试通过RedisTemplate方式进行String类型操作
     */
    @Test
    public void testOpsForValue() {
        Answer answer = new Answer();

        // 设置请求参数
        answer.setJudge_type("large");
        answer.setLang("cpp");
        answer.setQuestion_id(1);
        answer.setTest_mode(false);
        answer.setTyped_code("print(hello,world)!");

        ValueOperations valueOperations = redisTemplate.opsForValue();
        String keyName = "value_type:answer";
        valueOperations.set(keyName, answer);
        // 检查添加结果
        assertTrue(redisTemplate.hasKey(keyName));
        Answer answerFromRedis = (Answer) valueOperations.get(keyName);
        // 检查获取到的数据是否和原来的数据相同
        Assertions.assertEquals(JSON.toJSONString(answer), JSON.toJSONString(answerFromRedis));
    }
}
