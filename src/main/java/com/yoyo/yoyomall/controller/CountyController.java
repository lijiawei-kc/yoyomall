package com.yoyo.yoyomall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.entity.Province;
import com.yoyo.yoyomall.mapper.CityMapper;
import com.yoyo.yoyomall.mapper.CountyMapper;
import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.service.CountyService;
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
    CountyService countyService;

    //全查
    @GetMapping("/selectcounty")
    public R getAllProvince(){
       return countyService.getAllProvince();
    }
    //根据id查
    @GetMapping("/select")
    public R get8Id(String id){
        return countyService.get8Id(id);
    }
    //根据cid查
    @GetMapping("/selectByCid")
    public R get8Cid(String id){
       return  countyService.get8Cid(id);
    }
    @GetMapping("/selectByName")
    public  R get8Name(String name){return  countyService.selectByName(name);}
    @GetMapping("/selectByCname")
    public R get8Cname(String cname){return countyService.selectByCname(cname);}
    //根据县id获取完整地址
    @GetMapping("/selectAdr")
    public  R getAdr8Id(String id){
        return countyService.getAdr8Id(id);
    }
    //增加县
    @GetMapping("/save")
    public  R save(String name,String cname){
        return countyService.save(name,cname);
    }
    //修改县
    @GetMapping("/update")
    public R update(String name,String cid,String id){
        return countyService.update(name, cid, id);
    }

    @GetMapping("/delete")
    public R delete(String id){
        return countyService.delete(id);
    }







}

