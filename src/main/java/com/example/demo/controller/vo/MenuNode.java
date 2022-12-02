package com.example.demo.controller.vo;

import com.example.demo.entity.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuNode {
    private Integer id;
    private String path;
    private String name;
    private Integer pid = 0;
    private String title;
    private String component;
    private Boolean isLeaf;
    private List<MenuNode> children = new ArrayList<>();
    public MenuNode(Menu menu) {
        this.id = menu.getId();
        this.path = menu.getPath();
        this.pid = menu.getPid();
        this.title = menu.getTitle();
        this.component = menu.getComponent();
        this.isLeaf = menu.getIsLeaf();
    }
}
