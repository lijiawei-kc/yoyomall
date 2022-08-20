package com.yoyo.yoyomall.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YoyoException extends RuntimeException{
    private Integer code;
    private String msg;


}
