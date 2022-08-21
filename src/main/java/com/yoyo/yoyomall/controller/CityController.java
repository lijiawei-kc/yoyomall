package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.mapper.CityMapper;
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
    CityMapper cityMapper;
//全查
    @GetMapping("/selectcity")
    public List<City> getAllProvince(){
        return cityMapper.selectList(null);
    }
//根据id查
    @GetMapping("/select")
    public City get8Id(Integer id){
        return cityMapper.selectById(id);
    }

}

