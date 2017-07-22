package com.nz.simpleshop.web;

public class ResponseObject {

    private Object data;
    private String message;
    private boolean success;

    public ResponseObject(String message, boolean success) {
        this(null, message, success);
    }

    public ResponseObject(Object data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
