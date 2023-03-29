package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@TableName("t_menu")
@Data
public class Menu extends BaseEntity {
    private String path;
    private String name;
    private String pid = "0";
    private String title;
    private String component;
    private Boolean isLeaf = true;
    private Boolean isExternal = false;
    private Boolean isNewPage = false;
    @TableField(value = "`order`")
    private Integer order = 0;
}
