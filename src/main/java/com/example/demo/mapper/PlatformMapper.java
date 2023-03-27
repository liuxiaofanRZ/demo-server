package com.example.demo.mapper;

import com.example.demo.entity.Platform;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlatformMapper {
    int deleteByPrimaryKey(String id);

    int insert(Platform record);

    int insertSelective(Platform record);

    Platform selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Platform record);

    int updateByPrimaryKey(Platform record);
}