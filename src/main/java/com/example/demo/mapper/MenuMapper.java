package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Update("update t_menu set is_leaf=#{leaf} where id=#{id}")
    public int setMenuLeaf(String id, int leaf);
}
