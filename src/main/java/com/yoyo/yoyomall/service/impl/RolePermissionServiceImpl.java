package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.entity.RolePermission;
import com.yoyo.yoyomall.mapper.RolePermissionMapper;
import com.yoyo.yoyomall.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public Integer insert(RolePermission rolePermission) {
        int row = baseMapper.insert(rolePermission);
        return row;
    }

    @Override
    public Integer deleteByRid(String rid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid",rid);
        int rows = baseMapper.delete(queryWrapper);
        return rows;
    }

    @Override
    public Integer deleteByPid(String pid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid",pid);
        int rows = baseMapper.delete(queryWrapper);
        return rows;
    }

    @Override
    public List<String> getPermissionIdList(String rid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid",rid);
        List<RolePermission> selectList = baseMapper.selectList(queryWrapper);
        List<String> permissionIdList = new ArrayList<>();
        for (RolePermission rolePermission : selectList) {
            permissionIdList.add(rolePermission.getPid());
        }
        return permissionIdList;
    }
}
