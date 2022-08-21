package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.entity.Province;
import com.yoyo.yoyomall.mapper.CountyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 县 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@RestController
@RequestMapping("/yoyomall/county")
public class CountyController {

    @Autowired
    CountyMapper countyMapper;
    //全查
    @GetMapping("/selectcounty")
    public List<County> getAllProvince(){
        return countyMapper.selectList(null);
    }
    //根据id查
    @GetMapping("/select")
    public County get8Id(Integer id){
        return countyMapper.selectById(id);
    }

}

