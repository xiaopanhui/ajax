package com.example.ajax;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @PostMapping ("/test1")
    public Response test1(String value){

        return ResponseUtil.success(value);
    }
}
