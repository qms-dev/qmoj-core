package team.akina.qmoj.exception;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public abstract class CustomException extends RuntimeException {

    private String msg;
    private int status = -1;
    private Object[] args;

    public CustomException() {
        super();
    }

    public CustomException(String msg, Object... args) {
        super(msg);
        this.msg = msg;
        this.args = args;
    }

    public CustomException(String msg, Throwable e, Object... args) {
        super(msg, e);
        this.msg = msg;
        this.args = args;
    }

    /**
     * 使用特定类型的状态码返回异常实体
     */
    public <T extends CustomException> T withSpecifiedStatus(int status) {
        this.status = status;
        return (T) this;
    }

    /**
     * 根据自定义Exception生成log信息
     */
    public String getDetailLogInfo() {
        return "【错误信息】:" + this.getMsg() +
                "【堆栈信息】:" + Arrays.toString(this.getStackTrace()) + "\r\n" +
                "【相关参数:】:" + JSON.toJSONString(this.getArgs());
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

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object... args) {
        this.args = args;
    }
}
