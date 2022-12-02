package com.example.demo.controller;

import com.example.demo.entity.Demo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;




@RestController
public class DemoController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        return name;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam String name,@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getServletContext().getRealPath("/folder/");
        saveFile(file, path);
        return "上传成功";
    }
    public void saveFile(MultipartFile file, String path) throws IOException {
        File upDir = new File(path);
        if(!upDir.exists()) {
            upDir.mkdir();
        }
        System.out.println(path + file.getOriginalFilename());
        System.out.println(file.getOriginalFilename());
        String name = file.getOriginalFilename();
        File f = new File(path + "pic." + name.split("\\.")[1]);
        file.transferTo(f);
    }
}
