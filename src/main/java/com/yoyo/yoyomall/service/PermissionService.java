package com.yoyo.yoyomall.service;

import com.yoyo.yoyomall.entity.Goodstag;
import com.yoyo.yoyomall.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
public interface PermissionService extends IService<Permission> {
    Integer insert(Permission permission);
    List<Permission> permissionTree(String parentId);

}
