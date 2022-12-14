package com.example.demo.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private boolean success = true;
    private Integer code = 0;
    private String message = "";
    private T result;

    public static <T> Result<T> ok(T data, String msg) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setResult(data);
        r.setMessage(msg);
        return r;
    }

    // 删除
    public static <T> Result<T> deleteOk(T data) {
        return Result.ok(data, "删除成功");
    }
    // 添加
    public static <T> Result<T> addOk(T data) {
        return Result.ok(data, "添加成功");
    }
    // 查询
    public static <T> Result<T> listOk(T data) {
        return Result.ok(data, "查询成功");
    }
    // 编辑
    public static <T> Result<T> editOk(T data) {
        return Result.ok(data, "编辑成功");
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(false);
        r.setCode(CommonConstant.SC_INTERNAL_SERVER_ERROR_500);
        r.setMessage(msg);
        r.setResult(data);
        return r;
    }
}

