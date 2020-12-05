package team.akina.qmoj.constants;

import java.io.Serializable;

/**
 * 一个统一的返回规范，这个待协商
 *
 * @author chenbo
 * @date 2020/12/4 0:05
 */

public class Response<T> implements Serializable {

    private static final long serialVersionUID = 6341861816047074162L;


    /**
     * 定义统用的成功失败取值
     */
    public static final int SUCCESS_FLAG = 0;
    public static final int FAIL_FLAG = 0;


    private Integer status;
    private String msg;
    private T data;

    public Response(Integer status) {
        this.status = status;
    }

    public Response(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Response(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    // TODO: 2020/12/4 把常见的返回如success，fail等写成静态方法，方便包装返回
    public static <T> Response<T> success(T data) {
        Response ret = new Response(SUCCESS_FLAG);
        ret.setData(data);
        return ret;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
