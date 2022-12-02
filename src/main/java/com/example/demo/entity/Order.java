package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@TableName("t_order")
@Data
public class Order {

    @TableId(type = IdType.AUTO)
    private int id;
    private String orderTime;
    private String total;
    private int uid;
    private User user;
}
