package com.example.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HtmCo {
    @Autowired
    private  HttpRe httpRe;
    @PostMapping("/httpLogin")
    public String getData(){
        return null;
    }
}
