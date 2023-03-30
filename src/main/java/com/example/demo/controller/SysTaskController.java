package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.qo.BatchDeleteQo;
import com.example.demo.entity.SysTask;
import com.example.demo.service.SysTaskService;
import com.example.demo.service.SysTaskService;
import com.example.demo.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/sysTask")
public class SysTaskController {
        @Resource
        private SysTaskService sysTaskService;

        @GetMapping("/list")
        public Result<IPage<SysTask>> list(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {
            LambdaQueryWrapper<SysTask> query = new LambdaQueryWrapper<>();
            if (request.getParameter("title") != null) {
                query.eq(SysTask::getTitle, request.getParameter("title"));
            }
            if (request.getParameter("desc") != null) {
                query.like(SysTask::getDesc, request.getParameter("desc"));
            }
            Page<SysTask> page = new Page<>(pageNo, pageSize);
            IPage<SysTask> iPage = sysTaskService.page(page, query);
            return Result.listOk(iPage);
        }

        @PostMapping("/add")
        public Result<SysTask> add(@RequestBody SysTask sysTask) {
            sysTaskService.save(sysTask);
            return Result.addOk(sysTask);
        }

        @PostMapping("/edit")
        public Result<SysTask> edit(@RequestBody SysTask sysTask) {
            sysTaskService.updateById(sysTask);
            return Result.editOk(sysTask);
        }

        @PostMapping("/delete")
        public Result<SysTask> delete(@RequestBody SysTask sysTask) {
            sysTaskService.removeById(sysTask.getId());
            return Result.deleteOk(sysTask);
        }

        @PostMapping("/deleteBatch")
        public Result<SysTask> deleteBatch(BatchDeleteQo bdq) {
            sysTaskService.removeByIds(bdq.getIds());
            return Result.deleteOk(null);
        }
    }
