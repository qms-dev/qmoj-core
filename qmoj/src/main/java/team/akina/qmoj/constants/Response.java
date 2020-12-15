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
    public static final int FAIL_FLAG = -1;


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

    /**
     * 返回通用success静态方法
     *
     * @param data 数据实体
     * @param <T>  数据类型
     * @return 完整的响应实体
     */
    public static <T> Response<T> success(T data) {
        Response<T> ret = new Response<T>(SUCCESS_FLAG);
        ret.setData(data);
        return ret;
    }

    /**
     * 返回通用的带message的fail
     *
     * @param msg 错误信息
     * @return 完整的响应实体
     */
    public static <T> Response<T> fail(String msg) {
        Response<T> ret = new Response<T>(FAIL_FLAG);
        ret.setMsg(msg);
        return ret;
    }

    /**
     * 返回带指定错误码的fail静态方法
     *
     * @param status 指定错误码
     * @param msg    错误信息
     * @return 完整的响应实体
     */
    public static <T> Response<T> fail(int status, String msg) {
        Response<T> ret = new Response<T>(status);
        ret.setStatus(status);
        ret.setMsg(msg);
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
