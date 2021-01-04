package team.akina.qmoj.exception;

/**
 * 输入参数错误时引发的异常
 */
public class InvalidInputException extends CustomException {
    public InvalidInputException(String msg, Object... args) {
        super(msg, args);
    }

    public InvalidInputException(String msg, Throwable e, Object... args) {
        super(msg, e, args);
    }
}
