package com.example.zg.zglianxi01.controller;

import com.alibaba.fastjson.JSON;
import com.example.zg.zglianxi01.pojo.Users;
import com.example.zg.zglianxi01.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * AJAXA的同异步请求
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    //@GetMapping
    @RequestMapping("indexs")
    public String index(){
        return "upload1";
    }

    @RequestMapping("getUser")
    @ResponseBody
    public String getUser(String id)throws InterruptedException{
        Users users = new Users();
        Users users1 = userService.get(id);
        String s = JSON.toJSONString(users);  //就算数据库查不到也不会返回null
        if(users1 != null){
            s = JSON.toJSONString(users1);
        }
        //Thread.sleep(5000);
        return s;
    }
}
