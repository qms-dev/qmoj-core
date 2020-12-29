package team.akina.qmoj.exception;

/**
 * 没有找到数据时引发的异常
 */
public class DataNotFindException extends CustomException {
    public DataNotFindException(String msg, Object... args) {
        super(msg, args);
    }

    public DataNotFindException(String msg, Throwable e, Object... args) {
        super(msg, e, args);
    }
}
