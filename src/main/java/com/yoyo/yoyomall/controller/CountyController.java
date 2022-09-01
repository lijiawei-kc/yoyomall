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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    /**
     * 查询所有县
     * @return resp.data.countyList
     */
    @GetMapping("/selectcounty")
    @PreAuthorize("hasAnyAuthority('address-county-list')")
    public R getAllProvince(){
       return countyService.getAllProvince();
    }
//    /**
//     * 根据id查询县
//     * @param id 县id
//     * @return resp.data.county
//     */
//    @GetMapping("/select")
//    public R get8Id(String id){
//        return countyService.get8Id(id);
//    }
    /**
     * 根据cid查询县列表
     * @param cid 市id
     * @return resp.data.countyList
     */
    @GetMapping("/selectByCid")
    @PreAuthorize("hasAnyAuthority('address-county-list')")
    public R get8Cid(String cid){
       return  countyService.get8Cid(cid);
    }
    /**
     * 根据县名查询县列表
     * @param name 县名
     * @return resp.data.county
     */
    @GetMapping("/selectByName")
    @PreAuthorize("hasAnyAuthority('address-county-list')")
    public  R get8Name(String name){return  countyService.selectByName(name);}
    /**
     * 根据市名查询县列表
     * @param cname 市名
     * @return resp.data.countyList
     */
    @GetMapping("/selectByCname")
    @PreAuthorize("hasAnyAuthority('address-county-list')")
    public R get8Cname(String cname){return countyService.selectByCname(cname);}
    /**
     * 根据县id查询完整的地址
     * @param id 县id
     * @return 在resp的data里有三个属性,province,city,county
     */
    @GetMapping("/selectAdr")
    public  R getAdr8Id(String id){
        return countyService.getAdr8Id(id);
    }
    /**
     * 增加县
     * @param name,cname 县名，市名
     * @return resp.msg
     */
    @GetMapping("/save")
    @PreAuthorize("hasAnyAuthority('address-county-save')")
    public  R save(String name,String cname){
        return countyService.save(name,cname);
    }
    /**
     * 修改县(只允许修改县名和所属市)
     * @param name,cname,id 县名，市名，县id
     * @return resp.msg
     */
    @GetMapping("/update")
    @PreAuthorize("hasAnyAuthority('address-county-list')")
    public R update(String name,String cname,String id){
        return countyService.update(name,id,cname);
    }
    /**
     * 删除县
     * @param id 县id
     * @return resp.msg
     */
    @GetMapping("/delete")
    @PreAuthorize("hasAnyAuthority('address-county-list')")
    public R delete(String id){
        return countyService.delete(id);
    }







}

