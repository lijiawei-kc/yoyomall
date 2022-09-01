package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.service.ProvinceService;
import com.yoyo.yoyomall.utils.MathUtil;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.yoyo.yoyomall.entity.*;

import java.util.ArrayList;
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
   ProvinceService provinceService;
    /**
     * 省全查
     * @return resp.data.provinceList
     */
    @GetMapping("/selectprovince")
    @PreAuthorize("hasAnyAuthority('address-province-list')")
    public R getAllProvince(){
        return  provinceService.getAllProvince();
    }
//    /**
//     * 根据id查省
//     * @param id 市id
//     * @return resp.data.province
//     */
//    @GetMapping("/select")
//    public R get8Id(String id){
//       return provinceService.get8Id(id);
//    }
    /**
     * 根据省名查省
     * @param name 省名
     * @return resp.data.province
     */
    @GetMapping("/selectByName")
    @PreAuthorize("hasAnyAuthority('address-province-list')")
    public R get8Name(String name){return provinceService.get8Name(name);}

    /**
     * 新增省
     * @param name 省名
     * @return resp.msg
     */
    @GetMapping("/save")
    @PreAuthorize("hasAnyAuthority('address-province-save')")
    public R save(String name){
     return provinceService.save(name);
    }
    /**
     * 修改省
     * @param id,name 省id,省名
     * @return resp.msg
     */
    @GetMapping("/update")
    @PreAuthorize("hasAnyAuthority('address-province-list')")
    public  R update(String id,String name){ return provinceService.update(id,name);}
    /**
     * 删除省
     * @param id 省id
     * @return resp.msg
     */
    @GetMapping("/delete")
    @PreAuthorize("hasAnyAuthority('address-province-list')")
    public R delete(String id){
        return provinceService.delete(id);
    }

}

