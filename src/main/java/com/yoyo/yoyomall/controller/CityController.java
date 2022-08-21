package com.yoyo.yoyomall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.mapper.CityMapper;
import com.yoyo.yoyomall.service.CityService;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 市 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@RestController
@RequestMapping("/yoyomall/city")
public class CityController {
    @Autowired
    CityService cityService;

//全查
    @GetMapping("/selectcity")
    public R getAllProvince(){
        return  cityService.getAllProvince();
    }
//根据id查
    @GetMapping("/select")
    public R get8Id(String id){
       return cityService.get8Id(id);
    }
    //根据pid查
    @GetMapping("/selectByPid")
    public R get8Pid(String id){
       return cityService.get8Pid(id);
    }

    @GetMapping("/save")
    public R save(String name,String pid){
        return cityService.save(name,pid);
    }
}

