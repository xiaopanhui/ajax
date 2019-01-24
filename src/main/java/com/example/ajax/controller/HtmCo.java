package com.example.ajax.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.ajax.Response;
import com.example.ajax.ResponseUtil;
import com.example.ajax.service.HtmlParser;
import com.example.ajax.service.HttpRe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HtmCo {
    @Autowired
    private HttpRe httpRe;
    @Autowired
    private HtmlParser htmlParser;
    @PostMapping("/httpLogin")
    public String getData(String userName,String password,String url){
//        public String getData(@RequestBody Map map){
        JSONObject param = new JSONObject();
        param.put("userName",userName);
        param.put("password",password);
        Map<String, Object> headers=new HashMap<String, Object>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("Accept","application/json;charset=UTF-8");
        String data = null;
        try {
             data= httpRe.postForAPP(url, param, headers);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    @PostMapping("/parse")
    public Response  parse(String url){
        return  ResponseUtil.success( htmlParser.getJOnseParse(url));

    }
}
