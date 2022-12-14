package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_account")
@Data
public class Account extends BaseEntity {
    private String username;
    private String password;
    private String description;
    private String platformId;
}
