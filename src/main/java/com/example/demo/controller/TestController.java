package com.example.demo.controller;

import com.example.demo.entity.Platform;
import com.example.demo.mapper.PlatformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private PlatformMapper platformMapper;
    @GetMapping("/platform/list")
    public Platform getPlatformMapper() {
        return platformMapper.selectByPrimaryKey("1");
    }
}
