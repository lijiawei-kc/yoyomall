package com.yoyo.yoyomall.utils;

import java.util.List;

import static java.lang.Integer.parseInt;

public class MathUtil {
    public Integer findMaxId(List<String> list){
        int max=-1;
        for (String x:list) {
            int num=parseInt(x);
            if(max<num){
                max=num;
            }
        }
return max;
    }
}
