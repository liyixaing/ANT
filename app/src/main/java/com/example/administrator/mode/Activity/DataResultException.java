package com.example.administrator.mode.Activity;

import java.io.IOException;

/**
 * Created by Administrator on 2018/5/22/022.
 */

public class DataResultException  extends  IOException {


    private String message;
    private int code;

    public DataResultException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
