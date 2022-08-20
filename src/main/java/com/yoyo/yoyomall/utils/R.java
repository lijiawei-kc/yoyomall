package com.yoyo.yoyomall.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {

    private Integer code;
    private String msg;
    private Map<String,Object> data =new HashMap<>();

    public static R ok(){
        R r = new R();
        r.code(20000);
        r.msg("success");
        return r;
    }

    public static R error(){
        R r = new R();
        r.code(20001);
        r.msg("error");
        return r;
    }


    public R code(Integer code) {
        this.code = code;
        return this;
    }

    public R msg(String msg) {
        this.msg = msg;
        return this;
    }

    public R data(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

}
