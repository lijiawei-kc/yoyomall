package com.yoyo.yoyomall.service.impl;

import com.yoyo.yoyomall.entity.Permission;
import com.yoyo.yoyomall.mapper.PermissionMapper;
import com.yoyo.yoyomall.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.service.ResponseMessage;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
@Autowired
private  PermissionMapper permissionMapper;
    @Autowired
    private  PermissionService permissionService;
    @Override
    public Integer insert(Permission permission) {
        int rows = baseMapper.insert(permission);
        return rows;
    }

    @Override
    public List<Permission> permissionTree(String parentId) {

        List<Permission> childrenPermissionList = permissionMapper.permmissionTree(parentId);
        for (Permission permission : childrenPermissionList) {

            List<Permission> permissionTreeList= permissionTree(permission.getId());
            permission.setChildren(permissionTreeList);
        }
        return  childrenPermissionList;
    }
}
