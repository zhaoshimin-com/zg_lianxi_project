package com.example.zg.zglianxi01;

import com.example.zg.zglianxi01.pojo.MyBean;
import com.example.zg.zglianxi01.pojo.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sds_list {


    //list转换成json字符串
    @Test
    void afad(){
        User user = new User();
        user.setName("JSON");
        user.setAge(23);
        user.setAddress("北京市海淀区");
        List<User> list=new ArrayList<User>();
        list.add(user);

        JSONArray listArray=JSONArray.fromObject(list);

        System.out.println("listArray:"+listArray.toString());
    }

    //json字符串抓换成list
    @Test
    void ac1(){
        //定义一个json数据
        String arrayStr = "[{\"name\":\"赵世民\",\"age\":\"23\",\"address\":\"南京市秦淮区\"}]";
        //转换为list
        List<User> list2 = JSONArray.toList(JSONArray.fromObject(arrayStr),User.class);
        //List<User> list2 = (List<User>) JSONArray.toList(JSONArray.fromObject(arrayStr),User.class);
        for (User user:list2) {
            System.out.println(user);
        }
        //转换为数组
        User[] ss = (User[]) JSONArray.toArray(JSONArray.fromObject(arrayStr),User.class);
        for (User user1:ss) {
            System.out.println(user1);
        }
    }


    //map抓换成json字符串
    @Test
    void asq1(){
        User user = new User();
        user.setName("赵世民");
        user.setAge(22);
        user.setAddress("南京市雨花台区");
        Map<String ,User> map = new HashMap<String,User>();
        map.put("first",user);

        //JSONObject
        JSONObject mapObject = JSONObject.fromObject(map);
        System.out.println("mapObject:"+mapObject.toString());
        //JSONArray
        JSONArray mapArray=JSONArray.fromObject(map);
        System.out.println("mapArray:"+mapArray.toString());
    }

    //json字符串转换为map
    @Test
    void as2(){
        String strObject="{\"first\":{\"address\":\"中国上海\",\"age\":\"23\",\"name\":\"JSON\"}}";

        //JSONObject
        JSONObject jsonObject=JSONObject.fromObject(strObject);
        Map map=new HashMap();
        map.put("first",User.class);

        //使用了toBean方法，需要三个参数
        MyBean my=(MyBean)JSONObject.toBean(jsonObject, MyBean.class, map);
        System.out.println(my.getFirst());
    }
}
