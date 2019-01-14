package com.example.ajax;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Response<T> {
    private  int status;
    private String msg;
    private T data;
    public Response(int status, String msg){
        this.status = status;
        this.msg = msg;
    }
    public Response(int status, String msg, T data){
        this(status, msg);
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
