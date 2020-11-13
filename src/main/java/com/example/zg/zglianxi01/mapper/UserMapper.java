package com.example.zg.zglianxi01.mapper;

import com.example.zg.zglianxi01.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from users where id = #{id}")
    Users get(@Param("id")String id);

}
