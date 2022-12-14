package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.qo.BatchDeleteQo;
import com.example.demo.entity.Account;
import com.example.demo.entity.Platform;
import com.example.demo.service.PlatformService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/platform")
public class PlatformController {
    @Autowired
    private PlatformService platformService;

    @GetMapping("/list")
    public Result<IPage<Platform>> list(@RequestParam Integer pageNo, @RequestParam Integer pageSize, HttpServletRequest request) {
        LambdaQueryWrapper<Platform> query = new LambdaQueryWrapper<>();
        if (request.getParameter("title") != null) {
            query.like(Platform::getTitle, request.getParameter("title"));
        }
        Page<Platform> page = new Page<>(pageNo, pageSize);
        IPage<Platform> result = platformService.page(page, query);
        return Result.addOk(result);
    }

    @GetMapping("/listAll")
    public Result<List<Platform>> listAll(Platform platform) {
        LambdaQueryWrapper<Platform> query = new LambdaQueryWrapper<>();
        if (platform.getTitle() != null) {
            query.like(Platform::getTitle, platform.getTitle());
        }
        List<Platform> list = platformService.list(query);
        return Result.listOk(list);
    }

    @PostMapping("/add")
    public Result<Platform> add(@RequestBody Platform platform) {
        platformService.save(platform);
        return Result.addOk(platform);
    }

    @PostMapping("/edit")
    public Result<Platform> edit(@RequestBody Platform platform) {
        platformService.updateById(platform);
        return Result.editOk(platform);
    }

    @PostMapping("/delete")
    public Result<Platform> delete(@RequestBody Platform platform) {
        platformService.removeById(platform.getId());
        return Result.deleteOk(platform);
    }
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody BatchDeleteQo bdq) {
        platformService.removeByIds(bdq.getIds());
        return Result.deleteOk(null);
    }
}
