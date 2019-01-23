package com.example.ajax.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.ajax.service.HttpRe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.example.ajax.service.HttpRe.postForAPP;

@RestController
public class HtmCo {
    @Autowired
    private HttpRe httpRe;
    @PostMapping("/httpLogin")
    public String getData(String userName,String password){
        JSONObject param = new JSONObject();
        param.put("userName", userName);
        param.put("password",password);
        Map<String, Object> headers=new HashMap<String, Object>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("Accept","application/json;charset=UTF-8");
        String data = null;
        try {
             data=  postForAPP("http://localhost:5005/login", param, headers);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
