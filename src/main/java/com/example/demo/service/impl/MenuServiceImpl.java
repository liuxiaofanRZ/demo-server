package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Menu;
import com.example.demo.exception.DemoException;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuMapper menuMapper;


    public void addMenu(Menu menu) {
        String pid = menu.getPid();
        if(!pid.equals("0")) {
            menuMapper.setMenuLeaf(pid,0);
        }
        menu.setIsLeaf(true);
        this.save(menu);
    }

    public void editMenu(Menu menu) {
        Menu oldMenu = this.getById(menu.getId());

        this.updateById(menu);
        if(!menu.getPid().equals("0")) {
            menuMapper.setMenuLeaf(menu.getPid(),0);
        }
        if(!oldMenu.getPid().equals(menu.getPid())){
            menuMapper.setMenuLeaf(menu.getId(),0);
            int count = this.count(new LambdaQueryWrapper<Menu>().eq(Menu::getPid, oldMenu.getPid()));
            if(count == 0) {
                menuMapper.setMenuLeaf(oldMenu.getPid(), 1);
            }
        }
    }

    public void deleteMenuById(String id) {
        Menu menu = this.getById(id);
        if (menu == null) {
            throw new DemoException("未找到菜单信息");
        }
        String pid = menu.getPid();
        if (!pid.equals("0")) {
            int count = this.count(new LambdaQueryWrapper<Menu>().eq(Menu::getPid, pid));
            if (count == 1) {
                menuMapper.setMenuLeaf(pid, 1);
            }
        }

        menuMapper.deleteById(id);
        this.removeChildrenByPid(id);
    }

    public void removeChildrenByPid(String pid) {
        LambdaQueryWrapper<Menu> query = new LambdaQueryWrapper<Menu>().eq(Menu::getPid, pid);
        List<Menu> menuList = this.list(query);
        if (menuList != null && menuList.size() > 0) {
// 这样性能会好？
            this.remove(query);
            for (Menu menu : menuList) {
// 为什么jeecg中先调用了this.count()
                this.removeChildrenByPid(menu.getId());
            }
        }

    }
}
