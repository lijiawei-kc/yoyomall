package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.AdminRole;
import com.yoyo.yoyomall.mapper.AdminRoleMapper;
import com.yoyo.yoyomall.service.AdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.utils.YoyoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author serol
 * @since 2022-08-22
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    @Override
    public void saveList(List<String> role, String id) {
        try {
            for (int i = 0; i < role.size(); i++) {
                String rid = role.get(i);
                AdminRole adminRole = new AdminRole();
                adminRole.setAid(id);
                adminRole.setRid(rid);
                baseMapper.insert(adminRole);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"保存职位失败");
        }


    }

    @Override
    public List<String> selectList(String id) {
        List<String> list = new ArrayList<>();
        try {
            QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
            adminRoleQueryWrapper.eq("aid",id);
            List<AdminRole> adminRoles = baseMapper.selectList(adminRoleQueryWrapper);

            for (int i = 0; i < adminRoles.size(); i++) {
                AdminRole adminRole = adminRoles.get(i);
                list.add(adminRole.getRid());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"查询失败");

        }
        return list;
    }

    @Override
    public void deleteList(String id) {
        try {
            QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
            adminRoleQueryWrapper.eq("aid",id);
            baseMapper.delete(adminRoleQueryWrapper);
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"删除失败");
        }
    }
}
