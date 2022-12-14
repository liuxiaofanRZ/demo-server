package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.qo.BatchDeleteQo;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import com.example.demo.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @GetMapping("/list")
    public Result<IPage<Account>> list(@RequestParam Integer pageNo, @RequestParam Integer pageSize, HttpServletRequest request) {
        LambdaQueryWrapper<Account> query = new LambdaQueryWrapper<>();
        if (request.getParameter("platformId") != null) {
            query.eq(Account::getPlatformId, request.getParameter("platformId"));
        }
        if (request.getParameter("username") != null) {
            query.like(Account::getPlatformId, request.getParameter("username"));
        }
        Page<Account> page = new Page<>(pageNo, pageSize);
        IPage<Account> iPage = accountService.page(page, query);
        return Result.listOk(iPage);
    }

    @PostMapping("/add")
    public Result<Account> add(@RequestBody Account account) {
        accountService.save(account);
        return Result.addOk(account);
    }

    @PostMapping("/edit")
    public Result<Account> edit(@RequestBody Account account) {
        accountService.updateById(account);
        return Result.editOk(account);
    }

    @PostMapping("/delete")
    public Result<Account> delete(@RequestBody Account account) {
        accountService.removeById(account.getId());
        return Result.deleteOk(account);
    }

    @PostMapping("/deleteBatch")
    public Result<Account> deleteBatch(BatchDeleteQo bdq) {
        accountService.removeByIds(bdq.getIds());
        return Result.deleteOk(null);
    }
}
