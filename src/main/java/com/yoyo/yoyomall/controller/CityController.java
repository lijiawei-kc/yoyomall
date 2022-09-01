package com.yoyo.yoyomall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.mapper.CityMapper;
import com.yoyo.yoyomall.service.CityService;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    /**
     * 查询市列表
     * @return resp.data.cityList
     */
    @GetMapping("/selectcity")
    @PreAuthorize("hasAnyAuthority('address-city-list')")
    public R getAllCity(){
        return  cityService.getAllCity();
    }
//    /**
//     * 根据id查询市
//     * @param id 市id
//     * @return resp.data.city
//     */
//    @GetMapping("/select")
//    public R get8Id(String id){
//       return cityService.get8Id(id);
//    }
    /**
     * 根据省id查询市列表
     * @param pid 省id
     * @return resp.data.cityList
     */
    @GetMapping("/selectByPid")
    @PreAuthorize("hasAnyAuthority('address-city-list')")
    public R get8Pid(String pid){
       return cityService.get8Pid(pid);
    }
    /**
     * 根据市名查询市
     * @param name 市名
     * @return resp.data.cityList
     */
    @GetMapping("/selectByName")
    @PreAuthorize("hasAnyAuthority('address-city-list')")
    public  R get8Name(String name){return cityService.get8Name(name);}
    /**
     * 根据省名查询市列表
     * @param pname 省名
     * @return resp.data.cityList
     */
    @GetMapping("/selectByPname")
    @PreAuthorize("hasAnyAuthority('address-city-list')")
    public R get8Pname(String pname){return cityService.get8Pname(pname);}
    /**
     * 添加市
     * @param name,pname 市名，省名
     * @return R.msg
     */
    @GetMapping("/save")
    @PreAuthorize("hasAnyAuthority('address-city-save')")
    public R save(String name,String pname){
        return cityService.save(name,pname);
    }
    /**
     * 修改市(只允许修改市名和所属省)
     * @param name,pname,id 市名,省名,市id
     * @return R.msg
     */
    @GetMapping("/update")
    @PreAuthorize("hasAnyAuthority('address-city-list')")
    public R update(String name,String id,String pname){
        return cityService.update(name,id,pname);
    }

    /**
     * 删除市
     * @param id 市id
     * @return R.msg
     */
    @GetMapping("/delete")
    @PreAuthorize("hasAnyAuthority('address-city-list')")
    public R delete(String id){
        return cityService.delete(id);
    }
}

