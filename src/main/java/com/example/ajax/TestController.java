package com.example.ajax;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping ("/test1")
    public Response test1(Integer id,String name){

        return ResponseUtil.success(name);
    }
}
