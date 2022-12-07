package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Menu;

public interface MenuService extends IService<Menu> {
    public void addMenu(Menu menu);

    public void editMenu(Menu menu);

    public void deleteMenuById(String id);
}
