package com.example.demo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final String FIELD_SYS_CREATE_TIME = "createTime";
    private static final String FIELD_SYS_UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, FIELD_SYS_CREATE_TIME, Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, FIELD_SYS_UPDATE_TIME, Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, FIELD_SYS_UPDATE_TIME, Date.class, new Date()); // 起始版本 3.3.0(推荐)
    }
}