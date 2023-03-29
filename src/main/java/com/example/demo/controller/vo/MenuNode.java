package com.example.demo.controller.vo;

import com.example.demo.entity.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuNode {
    private String id;
    private String path;
    private String name;
    private String pid;
    private String title;
    private String component;
    private Integer order;
    private Boolean isLeaf;
    private Boolean isExternal;
    private Boolean isNewPage;
    private List<MenuNode> children;
    public MenuNode(Menu menu) {
        this.id = menu.getId();
        this.path = menu.getPath();
        this.pid = menu.getPid();
        this.title = menu.getTitle();
        this.component = menu.getComponent();
        this.name = menu.getName();
        this.isLeaf = menu.getIsLeaf();
        this.isNewPage = menu.getIsNewPage();
        this.order = menu.getOrder();
        this.isExternal = menu.getIsExternal();
    }
}
