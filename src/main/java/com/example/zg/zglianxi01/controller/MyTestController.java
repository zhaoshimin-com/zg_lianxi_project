package com.example.zg.zglianxi01.controller;

import com.example.zg.zglianxi01.pojo.Team;
import com.example.zg.zglianxi01.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RequestBody接收前端数据的demo
 */
@RestController
public class MyTestController {

    /**
     * @RequestBody 直接以String接收前端传过来的json数据
     *
     * @Param  jsonString
     *              json格式的字符串
     * @return  json格式的字符串
     *
     * postman测试，先通过某个方法上的指定url使前端传递给后端json参数，
     * 然后RequestBody的职责就是负责（接收前端传到后端的json数据的）
     * 然后在进行该方法体中的操作
     * 最后return返回的就是（已经传过来的json数据）
     *
     */
    @RequestMapping("mytest0")
    public String myTestController0(@RequestBody String jsonString){
        System.out.println(jsonString);
        return jsonString;
    }


    /**
     *以简单的User对象接收前端传过来的json数据（SpringMVC会智能的将符合要求的数据装配进该User对象中）
     *
     * @Param   user
     *          用户实体类模型
     * @return  User重写后的toStirng
     *
     *
     */
    @RequestMapping("mytest1")
    public String myTestController1(@RequestBody User user){
        System.out.println(user.toString());
        return user.toString();
    }


    /**
     *以较复杂的Team对象接收前端传过来的json数据（SpringMVC会智能的将符合要求的数据装配进该Teamr对象中）
     * 注：如果后端@RequestBody后的对象，持有了集合等，当前端向传参令该对象持有的该集合为空时，json字符串中，
     * 对应位置应该如"teamMembers":[]这么写；即：传递的json字符串必须要有key，否则请求会出错
     *
     * @Param   team
     *             团体实体类模型
     * @return  Team重写后的toString
     */
    @RequestMapping("mytest2")
    public String myTestController2(@RequestBody Team team){
        System.out.println(team.toString());
        return team.toString();
    }


    /**
     * @RequestBody与简单的@RequestParam同时使用
     * @Param user
     *        用户实体类模型
     */
    @RequestMapping("mytest3")
    public String myTestController3(@RequestBody User user, @RequestParam("token") String token){
        System.out.println(user.toString());
        System.out.println(token);
        return token + ">>>" + user.toString();
    }


    /**
     * @RequestBody与复杂的@RequestParam同时使用
     * 注：这里以集合或者以数组接收数据都可以
     * @Param user
     *        用户实体类模型
     * @Param arrays
     *              从‘key-value’中获取到的集合数组
     */
    @RequestMapping("mytest4")
    public String myTestController4(@RequestBody User user, @RequestParam("arrays")List<String> arrays){
        System.out.println(user.toString());
        StringBuffer sb = new StringBuffer();
        for (String array : arrays){
            sb.append(array);
            sb.append(" ");
            System.out.println(array);
        }
        return sb.toString() + user.toString();
    }


    /**
     * RequestBody装配请求体重的信息
     * 第二个参数不加注解，装配url中的参数信息
     *
     */
    @RequestMapping("mytest5")
    public String myTestController5(@RequestBody User user1,User user2){
        System.out.println(user1.toString());
        System.out.println(user2.toString());
        return user1.toString()+"\n"+user2.toString();
    }
}
