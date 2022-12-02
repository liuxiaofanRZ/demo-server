package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.controller.vo.MenuNode;
import com.example.demo.entity.Menu;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.service.MenuService;
import com.example.demo.util.Result;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Result<List<Menu>> getListById(@PathVariable Integer pid) {
        LambdaQueryWrapper<Menu> query = new LambdaQueryWrapper<>();
        query.eq(Menu::getPid, pid);
        List<Menu> list = menuService.list(query);
        return Result.ok(list, "查询成功！");
    }

    @GetMapping("/treeList")
    public Result<List<MenuNode>> queryTreeList() {
        List<Menu> list = menuService.list();
        return Result.ok(getTreeList(new ArrayList<MenuNode>(), list, 0), "查询成功！");
    }

    public List<MenuNode> getTreeList(List<MenuNode> treeList, List<Menu> list, Integer pid) {
        for (Menu menu : list) {
            if (pid.equals(menu.getPid())) {
                MenuNode menuNode = new MenuNode(menu);
                treeList.add(menuNode);
                if(!menu.getIsLeaf()) {
                    getTreeList(menuNode.getChildren(), list, menuNode.getPid());
                }
            }
        }
        return treeList;
    }

    @PostMapping("/add")
    public Result<Menu> add(@RequestBody Menu menu) {
        boolean res = menuService.save(menu);
        System.out.println("res:" + res);
        return Result.ok(menu, "添加成功！");
    }

    @PostMapping("/edit")
    public Result<Menu> edit(@RequestBody Menu menu) {
        boolean res = menuService.updateById(menu);
        if (res) {
            return Result.ok(menu, "编辑成功！");
        } else {
            return Result.error("编辑失败！", null);
        }
    }

    @PostMapping("/delete")
    public Result<Menu> delete(@RequestBody Menu menu) {
        boolean res = menuService.removeById(menu.getId());
        if (res) {
            return Result.ok(null, "删除成功！");
        } else {
            return Result.error("删除失败！", null);
        }
    }
}
