package team.akina.qmoj.exception;

/**
 * 爬取LeetCode内容时引发的异常
 */
public class LeetCodeCrawlerException extends CustomException {
    public LeetCodeCrawlerException(String msg) {
        super(msg);
    }

    public LeetCodeCrawlerException(String msg, Throwable e) {
        super(msg, e);
    }

    public LeetCodeCrawlerException(String msg, int status) {
        super(msg);
    }

    public LeetCodeCrawlerException(String msg, int status, Throwable e) {
        super(msg, e);
    }
}
