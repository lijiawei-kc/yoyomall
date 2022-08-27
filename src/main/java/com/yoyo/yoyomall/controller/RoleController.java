package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.entity.Role;
import com.yoyo.yoyomall.service.RoleService;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/manager/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    //添加职务
    public R insert(Role role){
        R response;
        try {
            roleService.insert(role);

            response=R.ok();
        }catch (Exception e){
            response=R.error().msg("添加职务失败");
        }
        return response;
    }

    //删除职务
    @PostMapping("/delete")
    public R delete(String id){
        R response;
        try {
            roleService.delete(id);
            response=R.ok();
        }catch (Exception e){
            response=R.error().msg("删除职务失败");
        }
        return response;
    }
    //修改职务
    @PostMapping("/update")
    public R update(Role role){
        R response;
        try {
            roleService.update(role);
            response=R.ok();
        }catch (Exception e){
            response=R.error().msg("修改职务失败");
        }
        return response;
    }

    //根据id查询职务信息
    @GetMapping("/select")
    public R selectById(String id){
        R response;
        try {
            Role role = roleService.selectById(id);
            response=R.ok().data("role",role);
        }catch (Exception e){
            response=R.error().msg("查询职务失败");
        }
        return response;
    }

    //分页查询职务列表
    @GetMapping("/selectall")
    public R selectAll(String des,Integer currentPage,Integer pageSize){
        R response;
        try {
            List<Role> roleList = roleService.selectAll(des, currentPage, pageSize);
            Integer total = roleService.count(des);
            response=R.ok().data("roleList",roleList).data("total",total).data("currentPage",currentPage).data("pageSize",pageSize);
        }catch (Exception e){
            response=R.error().msg("查询职务列表失败");
        }
        return response;
    }

    @GetMapping("/selectAllList")
    public R selectAllList(){
        R response;
        try {
            List<Role> roleList = roleService.selectAllList();
            response=R.ok().data("roleList",roleList);
        }catch (Exception e){
            response=R.error().msg("查询职务列表失败");
        }
        return response;
    }
}

