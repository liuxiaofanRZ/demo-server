package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_platform")
@Data
public class Platform extends BaseEntity implements Serializable {
    private String title;
    private String url;
}
