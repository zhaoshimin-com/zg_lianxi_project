package com.example.zg.zglianxi01.service;


import com.example.zg.zglianxi01.mapper.UserMapper;
import com.example.zg.zglianxi01.pojo.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public Users get(String id) {
        return userMapper.get(id);
    }

}
