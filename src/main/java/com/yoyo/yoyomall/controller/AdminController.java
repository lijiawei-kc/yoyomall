package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.entity.Admin;
import com.yoyo.yoyomall.entity.vo.AdminVo;
import com.yoyo.yoyomall.service.AdminRoleService;
import com.yoyo.yoyomall.service.AdminService;
import com.yoyo.yoyomall.utils.R;
import com.yoyo.yoyomall.utils.YoyoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private AdminRoleService adminRoleService;


    //TODO
    @PostMapping("/login")
    public  R login(AdminVo admin){
        try {

        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"登陆失败");
        }

        return R.ok();
    }


    @GetMapping("/test")
    public  R test(){
      throw new YoyoException(20001,"////");
//        return R.ok();
    }

    @PostMapping("/save")
    public  R save(AdminVo admin){
        try {
            adminService.insert(admin);
        }catch (Exception e){
            throw new YoyoException(20001,"保存失败");
        }
        return R.ok();
    }

    @PostMapping("/select/{id}")
    public  R selectById(@PathVariable String id){
        try {
            Admin admin = adminService.getById(id);
          List<String> roleList = adminRoleService.selectList(admin.getId());
            AdminVo adminVo = new AdminVo();
            BeanUtils.copyProperties(admin,adminVo);
            adminVo.setRole(roleList);
            return R.ok().data("admin",adminVo);
        }catch (Exception e){
            throw new YoyoException(20001,"查询失败");
        }
    }
    @PostMapping("/selectall")
    public  R selectall(){
        try {
            List<Admin> list = adminService.list(null);
            return R.ok().data("list",list);
        }catch (Exception e){
            throw new YoyoException(20001,"查询失败");
        }
    }

    @PostMapping("/delete/{id}")
    public  R deleteById(@PathVariable String id){
        try {
            adminService.deleteById(id);
            return R.ok();
        }catch (Exception e){
            throw new YoyoException(20001,"失败");
        }
    }

    @PostMapping("/update")
    public  R update(AdminVo admin){
        try {
            adminService.updateByEntity(admin);

        }catch (Exception e){
            throw new YoyoException(20001,"保存失败");
        }
        return R.ok();
    }






}

