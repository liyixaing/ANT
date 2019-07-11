package com.example.administrator.mode.ethutlis;

public class ServiceExpection extends Exception {

    public int code;

    public String message;

    public ServiceExpection( int code, String message )
    {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
