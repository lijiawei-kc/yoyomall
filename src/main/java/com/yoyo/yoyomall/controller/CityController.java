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
    /**
     * 查询市列表
     *
     * @return adminVo
     */
    @GetMapping("/selectcity")
    public R getAllCity(){
        return  cityService.getAllCity();
    }
//根据id查
    /**
     * 查询市列表
     *
     * @return adminVo
     */
    @GetMapping("/select")
    public R get8Id(String id){
       return cityService.get8Id(id);
    }
    //根据pid查(一个省里的所有市)
    @GetMapping("/selectByPid")
    public R get8Pid(String id){
       return cityService.get8Pid(id);
    }
    @GetMapping("/selectByName")
    public  R get8Name(String name){return cityService.get8Name(name);}

    @GetMapping("/selectByPname")
    public R get8Pname(String pname){return cityService.get8Pname(pname);}
    //添加市
    @GetMapping("/save")
    public R save(String name,String pname){
        return cityService.save(name,pname);
    }
    //修改市
    @GetMapping("/update")
    public R update(String name,String pid,String id){
        return cityService.update(name,pid,id);
    }
    //删除市
    @GetMapping("/delete")
    public R delete(String id){
        return cityService.delete(id);
    }
}

