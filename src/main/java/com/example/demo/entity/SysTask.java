package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@TableName("sys_task")
@Data
public class SysTask extends BaseEntity {
    private String title;
    @TableField("`desc`")
    private String desc;
}