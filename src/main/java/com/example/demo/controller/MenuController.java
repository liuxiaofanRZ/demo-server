package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.vo.MenuNode;
import com.example.demo.entity.Menu;
import com.example.demo.controller.qo.BatchDeleteQo;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.service.MenuService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuMapper menuMapper;

    @GetMapping("/list")
    public Result<IPage<Menu>> getList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (request.getParameter("title") != null) {
            queryWrapper.like("title", request.getParameter("title"));
        }
        Page<Menu> page = new Page<>(pageNo, pageSize);
        IPage<Menu> pageList = menuService.page(page, queryWrapper);
        return Result.ok(pageList, "查询成功！");
    }

    @GetMapping("/listByPid/{pid}")
    public Result<List<Menu>> getListById(@PathVariable String pid) {
        LambdaQueryWrapper<Menu> query = new LambdaQueryWrapper<>();
        query.eq(Menu::getPid, pid);
        List<Menu> list = menuService.list(query);
        return Result.ok(list, "查询成功！");
    }

    @GetMapping("/treeList")
    public Result<List<MenuNode>> queryTreeList() {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Menu::getOrder);
        List<Menu> list = menuService.list(queryWrapper);
        return Result.ok(getTreeList(new ArrayList<MenuNode>(), list, "0"), "查询成功！");
    }

    public List<MenuNode> getTreeList(List<MenuNode> treeList, List<Menu> list, String pid) {
        for (Menu menu : list) {
            if (pid.equals(menu.getPid())) {
                MenuNode menuNode = new MenuNode(menu);
                treeList.add(menuNode);
                if (!menu.getIsLeaf()) {
                    menuNode.setChildren(new ArrayList<>());
                    getTreeList(menuNode.getChildren(), list, menuNode.getId());
                }
            }
        }
        return treeList;
    }

    @PostMapping("/add")
    public Result<Menu> add(@RequestBody Menu menu) {
        menuService.addMenu(menu);
        return Result.ok(menu, "添加成功！");
    }

    @PostMapping("/edit")
    public Result<Menu> edit(@RequestBody Menu menu) {
        try {
            menuService.editMenu(menu);
            return Result.ok(menu, "编辑成功！");

        } catch (Exception e) {
            return Result.error("编辑失败！", null);

        }
    }

    @PostMapping("/delete")
    public Result<Menu> delete(@RequestBody Menu menu) {
        try {
            menuService.deleteMenuById(menu.getId());
            return Result.ok(null, "删除成功！");
        } catch (Exception e) {
            return Result.error("删除失败！", null);
        }
    }

    @PostMapping("/deleteBatch")
    public Result<Menu> delete(@RequestBody BatchDeleteQo batchDeleteQo) {
        try {
            for (String id : batchDeleteQo.getIds()) {
                menuService.deleteMenuById(id);
            }
            return Result.ok(null, "删除成功！");
        } catch (Exception e) {
            return Result.error("删除失败！", null);
        }
    }
}
