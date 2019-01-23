package com.example.ajax.controller;

import com.example.ajax.bean.City;
import com.example.ajax.bean.ResponseResult;
import com.example.ajax.bean.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
//    //响应客户端的数据为state：1，message：成功
//    @RequestMapping("/test1.do")
//    //@ResponseBody表示响应页面
//
//    public ResponseResult<Void> test1() {
//        //封装了状态码和信息
//        ResponseResult<Void> rr = new ResponseResult<Void>(1, "成功");
//        return rr;
//    }

    //返回对象
    @RequestMapping("/test2.do")
  
    public ResponseResult<User> test2() {
        //1.创建rr对象
        ResponseResult<User> rr =
                new ResponseResult<User>(1, "成功");
        //2.设置data属性值为user对象
        User user = new User();
        user.setId(1);
        user.setName("admin");
        user.setPhone("13800138000");
        user.setAddress("中鼎8层");
        rr.setData(user);
        //3.返回rr对象
        return rr;
    }

    //返回list
    @RequestMapping("/test3.do")
  
    public ResponseResult<List<User>> test3() {
        //1.创建rr对象
        ResponseResult<List<User>> rr =
                new ResponseResult<List<User>>(1, "成功");
        //2.设置data属性值为list
        User user1 = new User();
        user1.setId(1);
        user1.setName("小李");
        user1.setPhone("13800138000");
        user1.setAddress("中鼎7层");

        User user2 = new User();
        user2.setId(2);
        user2.setName("小刘");
        user2.setPhone("13900139000");
        user2.setAddress("中鼎8层");

        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);

        rr.setData(list);

        //3.返回rr对象
        return rr;
    }

    //显示update 页面
    @RequestMapping("/showUpdate.do")
    public String showUpdate() {
        return "update";
    }


    //异步请求，处理返回User对象数据
    @RequestMapping("/showData.do")
  
    public ResponseResult<User> showData() {
        //1.创建rr对象
        ResponseResult<User> rr =
                new ResponseResult<User>(1, "成功");
        //2.设置data属性值为user
        User user = new User();
        user.setName("小王");
        user.setPhone("13800138000");
        user.setAddress("中鼎大厦8层");
        rr.setData(user);
        //3.reurn rr;
        return rr;
    }

    //获取城市信息
    @PostMapping("/getCity.do")
  
    public ResponseResult<List<City>> getCity(Integer code) {
        //定义集合
        List<City> list = new ArrayList<City>();
        //如果是河北省，设置城市信息，添加到list
        if (code == 1) {
            list.add(new City("11", "石家庄"));
            list.add(new City("12", "秦皇岛"));
        } else {
            //如果是辽宁省，设置城市信息，添加到list
            list.add(new City("21", "沈阳"));
            list.add(new City("22", "大连"));
        }
        //创建rr对象
        ResponseResult<List<City>> rr = new ResponseResult<List<City>>(1, "成功");
        //设置data的值为list
        rr.setData(list);
        //返回rr对象
        return rr;
    }
}










