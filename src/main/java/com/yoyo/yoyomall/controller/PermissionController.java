package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.entity.Permission;
import com.yoyo.yoyomall.service.PermissionService;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@RestController
@RequestMapping("/yoyomall/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 添加权限
     * @param permission
     * @return R
     */
    @RequestMapping("/add")
    public R insert  (Permission permission){

        try {
            permissionService.insert(permission);
            return R.ok().msg("添加权限成功");
        }catch (Exception e){
            return R.error().msg("添加权限失败");

        }
    }

}

