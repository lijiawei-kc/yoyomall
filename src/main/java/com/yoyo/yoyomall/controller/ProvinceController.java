package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.service.ProvinceService;
import com.yoyo.yoyomall.utils.R;
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
    //全查
    @GetMapping("/selectprovince")
    public R getAllProvince(){
        List<Province> list=provinceMapper.selectList(null);
        return R.ok().data("provinceList",list);
    }
    //根据id查
    @GetMapping("/select")
    public R get8Id(Integer id){
        Province province= provinceMapper.selectById(id);
        return R.ok().data("province",province);
    }
}

