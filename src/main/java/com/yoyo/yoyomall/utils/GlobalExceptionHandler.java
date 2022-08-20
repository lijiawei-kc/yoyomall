package com.yoyo.yoyomall.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().msg("全局异常处理");
    }
    @ExceptionHandler(YoyoException.class)
    @ResponseBody
    public R error(YoyoException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().msg(e.getMsg()).code(e.getCode());
    }
}