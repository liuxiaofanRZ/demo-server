package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from t_user")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "birthday", property = "birthday"),
            @Result(
                    column = "id",
                    property = "orders",
                    javaType = List.class,
                    many = @Many(select = "com.example.demo.mapper.OrderMapper.selectById"))
    })
    List<User> selectAll();

    @Select("select * from t_user where id = #{id}")
    User selectById(int id);
}
