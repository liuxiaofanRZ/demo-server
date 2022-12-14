package com.example.demo.handler;

import com.example.demo.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        System.out.println("*********************全局错误处理");
        System.out.println(e);
        return Result.error(e.getMessage(),null);
    }
}
