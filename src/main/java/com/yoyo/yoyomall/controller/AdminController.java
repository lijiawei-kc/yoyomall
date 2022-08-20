package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.service.AdminService;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/manager/admin")
@CrossOrigin  //跨域
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/test")
    public  R test(){
        adminService.test();
        return R.ok();
    }



}

