package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.yoyo.yoyomall.entity.*;

import java.util.List;

/**
 * <p>
 * 省 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@RestController
@RequestMapping("/yoyomall/province")
public class ProvinceController {

    @Autowired
    ProvinceMapper provinceMapper;
    @GetMapping("/all")
    public List<Province> getAllProvince(){
        return provinceMapper.selectList(null);
    }


}

