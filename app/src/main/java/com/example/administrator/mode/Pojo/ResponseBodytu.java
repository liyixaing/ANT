package com.example.administrator.mode.Pojo;

/**
 * Created by Administrator on 2018/5/26/026.
 */

public class ResponseBodytu {


    /**
     * code : 1
     * message : success
     * data : http://www.domain.com/images/1.jpg
     */

    private int code;
    private String message;
    private String data;

    @Override
    public String toString() {
        return "ResponseBodytu{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
