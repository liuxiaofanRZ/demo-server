package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static java.lang.System.out;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    // 列表查询
    @GetMapping("/list")
    public Result<IPage<User>> getList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (request.getParameter("username") != null) {
            queryWrapper.like("username", request.getParameter("username"));
        }
        queryWrapper.orderByDesc("id");
        Page<User> page = new Page<>(pageNo, pageSize);
        IPage<User> pageList = userService.page(page, queryWrapper);
        Result<IPage<User>> result = Result.ok(pageList, "查询成功！");
//        return ResponseEntity.status(400).body(result);
        return result;
    }

    // 添加
    @PostMapping("/add")
    public Result<User> addUser(@RequestBody User user) {
        out.println(user);
        Boolean res = userService.save(user);
        out.println(res);
        Result<User> result = Result.ok(user, "添加用户成功！");
        return result;
    }

    // 编辑
    @PostMapping("/edit")
    public Result<User> editUser(@RequestBody User user) {
        if (user.getId() == null) {
            return Result.error("id不存在", null);
        }
        Boolean res = userService.updateById(user);
        return Result.ok(null, "编辑成功");
    }

    // 删除
    @PostMapping("/delete")
    public Result<?> deleteUser(@RequestBody User user) {
        userService.removeById(user.getId());
        return Result.ok(null, "删除成功！");
    }

}
