package com.example.ajax;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginTest {

    public static void main(String[] args) {
//        System.out.println(yanzheng());
//        login();
//        yan();

    }

    public static String yanzheng() {
        YanZhengMa yanZhengMa = new YanZhengMa();

        String url = "http://www.miibeian.gov.cn/getBlacklistWebsiteVerifyCode?6";
        HttpRe httpRe = new HttpRe();
        return yanZhengMa.yanzhengma(httpRe.getImage(url));
    }

//    public static String yan() {
//
//        YanZhengMa2 yanZhengMa = new YanZhengMa2();
//
//        String url = "";
//        HttpRe httpRe = new HttpRe();
//        return yanZhengMa.yanzhengma(httpRe.getImage(url));
//    }

    public static String login() {
        String url = "https://www.tianyancha.com/cd/login.json";
        HttpRe httpRe = new HttpRe();

        JSONObject param = new JSONObject();
        param.put("mobile", "18285107937");
        param.put("loginway", "PL");
        param.put("cdpassword", "b0baee9d279d34fa1dfd71aadb908c3f");
        param.put("autoLogin", true);
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("Accept", "application/json;charset=UTF-8");
        try {
            return httpRe.postForAPP(url, param, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
