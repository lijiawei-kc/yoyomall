package com.yoyo.yoyomall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.entity.Province;
import com.yoyo.yoyomall.mapper.CountyMapper;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public R getAllProvince(){
        List<County> list=countyMapper.selectList(null);
        return R.ok().data("countyList",list);
    }
    //根据id查
    @GetMapping("/select")
    public R get8Id(Integer id){
        County county= countyMapper.selectById(id);
        return R.ok().data("county",county);
    }
    //根据cid查
    @GetMapping("/selectByCid")
    public R get8Cid(Integer id){
        QueryWrapper<County> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_id",id);
        List<County> list=countyMapper.selectList(queryWrapper);
        return R.ok().data("countryList",list);
    }





}

