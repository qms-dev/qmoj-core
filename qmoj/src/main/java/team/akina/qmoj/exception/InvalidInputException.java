package team.akina.qmoj.exception;

public class InvalidInputException extends CustomException {
    public InvalidInputException(String msg) {
        super(msg);
    }

    public InvalidInputException(String msg, Throwable e) {
        super(msg, e);
    }

    public InvalidInputException(String msg, int status) {
        super(msg);
    }

    public InvalidInputException(String msg, int status, Throwable e) {
        super(msg, e);
    }
}
