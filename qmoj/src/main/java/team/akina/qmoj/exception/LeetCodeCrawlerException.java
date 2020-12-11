package team.akina.qmoj.exception;

/**
 * 爬取LeetCode内容时引发的异常
 */
public class LeetCodeCrawlerException extends CustomException {
    public LeetCodeCrawlerException(String msg, Object... args) {
        super(msg, args);
    }

    public LeetCodeCrawlerException(String msg, Throwable e, Object... args) {
        super(msg, e, args);
    }
}
