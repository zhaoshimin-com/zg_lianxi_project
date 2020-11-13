package com.example.zg.zglianxi01;


import com.example.zg.zglianxi01.pojo.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

public class aaa {
    @Test
    void afdf(){
        //定义两种不同格式的字符串
        String objectStr="{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}";
        String arrayStr ="[{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}]";

        //1.使用JSONObject
        JSONObject jsonObject=JSONObject.fromObject(objectStr);
        User user= (User) JSONObject.toBean(jsonObject,User.class);


        //2.使用JSONArray
        JSONArray jsonArray=JSONArray.fromObject(arrayStr);
        //获得jsonArray的第一个元素
        Object o=jsonArray.get(0);
        JSONObject jsonObject1=JSONObject.fromObject(o);
        User user1=(User)JSONObject.toBean(jsonObject1,User.class);
        System.out.println("stu:"+user);
        System.out.println("stu1:"+user1);

    }



    


}
