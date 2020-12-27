package team.akina.qmoj.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.akina.qmoj.constants.Constants;
import team.akina.qmoj.utils.http.HttpRequestHelper;

import java.io.IOException;

@SpringBootTest
public class HttpClientTest {

    @Autowired
    private HttpRequestHelper httpRequestHelper;

    /**
     * 测试多线程条件下通过HttpClient发起的http请求是否是排队执行的
     */
    @Test
    void multiThreadRequest() throws IOException {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Thread requestThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(finalI * 50);
                        httpRequestHelper.doGet(Constants.LEETCODE_URL, null);
                        System.out.println(Thread.currentThread().getName());
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, Integer.toString(i));
            requestThread.start();
        }

        // 等待请求结束
        System.in.read();
    }
}
