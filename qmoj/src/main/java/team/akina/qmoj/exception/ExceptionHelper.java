package team.akina.qmoj.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.akina.qmoj.constants.Response;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class ExceptionHelper {

    // todo 日志需要进行简单配置，配置为可以按照日期，类型进行分类
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);

    /**
     * 输入参数有误产生的异常
     */
    @ExceptionHandler(value = {InvalidInputException.class})
    public Response handleInvalidInputException(InvalidInputException ex) {
        logger.error(ex.getDetailLogInfo());
        return Response.fail("输入参数异常:" + ex.getMsg());
    }

    /**
     * LeetCode爬取过程中产生的异常
     */
    @ExceptionHandler(value = {LeetCodeCrawlerException.class})
    public Response handleLeetCodeCrawlerException(LeetCodeCrawlerException ex) {
        logger.error(ex.getDetailLogInfo());
        return Response.fail("请求LeetCode发生异常:" + ex.getMsg());
    }

    /**
     * 没有找到数据时引发的异常
     */
    @ExceptionHandler(value = {DataNotFindException.class})
    public Response dataNotFindException(DataNotFindException ex) {
        logger.error(Arrays.toString(ex.getStackTrace()));
        return Response.fail("查询数据异常:" + ex.getMsg());
    }

    /**
     * 兜底的异常
     */
    @ExceptionHandler(Exception.class)
    public Response runtimeException(Exception ex) {
        logger.error(Arrays.toString(ex.getStackTrace()));
        return Response.fail("系统异常！请稍后重试！");
    }
}
