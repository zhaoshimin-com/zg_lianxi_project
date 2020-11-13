package com.example.zg.zglianxi01;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zg.zglianxi01.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class Zglianxi01ApplicationTests {


        public static String jsonmap = "{\"001\":{\"name\":\"xiaohong\",\"password\":\"654321\"},\"002\":[{\"$ref\":\"$.001\"},{\"name\":\"xixi\",\"password\":\"789\"}]}";
        public static String jsonuser = "{\"name\":\"xiaohong\",\"password\":\"654321\"}";
        public static String jsonlist = "[{\"name\":\"xiaohong\",\"password\":\"654321\"},{\"name\":\"xixi\",\"password\":\"789\"}]";

    @Test
    void contextLoads() {
            User user = new User();
            user.setName("xiaohong");
            user.setPassword("654321");
            List<User> list = new ArrayList<User>();
            list.add(user);
            //list.add(new User("vgff","789"));
            Map map = new HashMap();
            map.put("001",user);
            map.put("002",list);
            // 1. json字符串 转换为 java 对象


            // toJsonString  - java对象转换为json字符串
            System.out.println(JSONObject.toJSONString(user));
            System.out.println(JSONObject.toJSONString(list));
            System.out.println(JSONObject.toJSONString(map));

            // toJavaObject - json字符串转换为java对象
            System.out.println(JSONObject.parseObject(jsonuser,User.class)+"json字符串转换为java对象111");
            List list1 = JSONObject.parseObject(jsonlist, List.class);
            for(int i=0;i<list1.size();i++) {
                System.out.println(JSONObject.parseObject(JSONObject.toJSONString(list1.get(i)),User.class)+"json字符串转换为java对象");
            }
            Map map1 = JSONObject.parseObject(jsonmap, Map.class);
            Iterator iterator = map1.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry= (Map.Entry) iterator.next();
                System.out.println("key :"+entry.getKey()+"   value: " + entry.getValue());
            }
            // Json  -- 实体类转换为json字符串
            System.out.println(JSON.toJSONString(user));
            System.out.println(JSON.toJSONString(list));
            System.out.println(JSON.toJSONString(map));
            // json  -- json 转 java对象
            System.out.println(JSON.parseObject(jsonuser,User.class));
            List list2 = JSON.parseObject(jsonlist, List.class);
            for(int i=0;i<list2.size();i++) {
                System.out.println(JSON.parseObject(JSON.toJSONString(list1.get(i)),User.class));
            }
            Map map2 = JSON.parseObject(jsonmap, Map.class);
            Iterator iterator2 = map1.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry= (Map.Entry) iterator2.next();
                System.out.println("key :"+entry.getKey()+"   value: " + entry.getValue());
            }


        }

    }


