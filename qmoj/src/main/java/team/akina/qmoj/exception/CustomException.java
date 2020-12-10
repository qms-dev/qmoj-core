package team.akina.qmoj.exception;

public abstract class CustomException extends RuntimeException {

    private String msg;
    private int status = -1;

    public CustomException()
    {
        super();
    }

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CustomException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CustomException(String msg, int status) {
        super(msg);
        this.msg = msg;
        this.status = status;
    }

    public CustomException(String msg, int status, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return status;
    }

    public void setCode(int status) {
        this.status = status;
    }
}
