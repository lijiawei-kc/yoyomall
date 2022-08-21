package com.yoyo.yoyomall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.mapper.CityMapper;
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
    CityMapper cityMapper;
//全查
    @GetMapping("/selectcity")
    public R getAllProvince(){
        List<City> list=cityMapper.selectList(null);
        return R.ok().data("cityList",list);
    }
//根据id查
    @GetMapping("/select")
    public R get8Id(Integer id){
        City city=cityMapper.selectById(id);
        return R.ok().data("city",city);
    }
    //根据pid查
    @GetMapping("/selectByPid")
    public R get8Pid(Integer id){
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id",id);
        List<City> list=cityMapper.selectList(queryWrapper);
        return R.ok().data("countryList",list);
    }

}

