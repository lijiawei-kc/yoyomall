package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.yoyo.yoyomall.entity.Role;
import com.yoyo.yoyomall.entity.RolePermission;
import com.yoyo.yoyomall.mapper.RoleMapper;
import com.yoyo.yoyomall.service.RolePermissionService;
import com.yoyo.yoyomall.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    @Transactional //开启事务
    public Integer insert(Role role, String[] permissionIdList) {
        int row = baseMapper.insert(role);
        String rid = role.getId();

        for (String pid : permissionIdList) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRid(rid);
            rolePermission.setPid(pid);
            rolePermissionService.insert(rolePermission);
        }
        return row;
    }

    @Override
    public Integer update(Role role, String[] permissionIdList) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",role.getId());
        int row = baseMapper.update(role,updateWrapper);
        String rid = role.getId();

        rolePermissionService.deleteByRid(rid);

        for (String pid : permissionIdList) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRid(rid);
            rolePermission.setPid(pid);
            rolePermissionService.insert(rolePermission);
        }
        return row;
    }

    @Override
    public Integer delete(String id) {
        int row = baseMapper.deleteById(id);

        rolePermissionService.deleteByRid(id);
        return row;
    }

    @Override
    public Role selectById(String id) {
        Role role = baseMapper.selectById(id);

        List<String> permissionIdList = rolePermissionService.getPermissionIdList(id);

        role.setPermissionIdList(permissionIdList);
        return role;
    }

    @Override
    public List<Role> selectAll(String des, Integer currentPage, Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper();
        queryWrapper.apply(!StringUtils.isNullOrEmpty(des),"match(des) against ('"+des+"')");

        Page<Role> page = new Page<>(currentPage,pageSize);

        List<Role> roleList = this.page(page, queryWrapper).getRecords();

        for (Role role : roleList) {
            List<String> permissionIdList = rolePermissionService.getPermissionIdList(role.getId());
            role.setPermissionIdList(permissionIdList);
        }


        return roleList;
    }

    @Override
    public Integer count(String des) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.apply(!StringUtils.isNullOrEmpty(des),"match(des) against ('"+des+"')");
        Integer count = baseMapper.selectCount(queryWrapper);
        return count;
    }
}
