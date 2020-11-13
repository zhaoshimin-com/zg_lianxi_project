package com.example.zg.zglianxi01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 各个页面的跳转
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/shangchuan")
    public String shangchuan(){
        return "shangchuan";
    }

}