package com.yoyo.yoyomall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yoyo.yoyomall.entity.RolePermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-22
 */
public interface RolePermissionService extends IService<RolePermission> {
    public Integer insert(RolePermission rolePermission);

    public Integer deleteByRid(String rid);

    public Integer deleteByPid(String pid);

    public List<String> getPermissionIdList(String rid);

}
