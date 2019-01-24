package com.example.ajax;

public class ResponseUtil<T> {
    public static <T> Response success(T data){
        return new Response(200, "success", data);
    }

    public static <T> Response error(){
        return new Response(500, "error");
    }
}
