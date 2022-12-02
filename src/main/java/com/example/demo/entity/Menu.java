package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_menu")
@Data
public class Menu {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String path;
    private String name;
    private Integer pid = 0;
    private String title;
    private String component;
    private Boolean isLeaf;
}
