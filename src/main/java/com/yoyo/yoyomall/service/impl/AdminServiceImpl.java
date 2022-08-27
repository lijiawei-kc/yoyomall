package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.Admin;
import com.yoyo.yoyomall.entity.Permission;
import com.yoyo.yoyomall.entity.Role;
import com.yoyo.yoyomall.entity.vo.AdminVo;
import com.yoyo.yoyomall.mapper.AdminMapper;
import com.yoyo.yoyomall.service.AdminRoleService;
import com.yoyo.yoyomall.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.service.PermissionService;
import com.yoyo.yoyomall.utils.YoyoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-20
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
   @Autowired
   private AdminRoleService adminRoleService;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Autowired
   private PermissionService permissionService;


    public void test(){

   }

    @Override
    @Transactional
    public void deleteById(String id) {
       try {
           baseMapper.deleteById(id);
           adminRoleService.deleteList(id);

       }catch (Exception e){
           e.printStackTrace();
           throw new YoyoException(20001,"删除失败");
       }

    }

    @Override
    @Transactional
    public void insert(AdminVo admin) {
        try {
            Admin admin1 = new Admin();
            BeanUtils.copyProperties(admin,admin1);
            admin1.setPassword(passwordEncoder.encode(admin1.getPassword()));
            baseMapper.insert(admin1);
            List<String> role = admin.getRole();
            adminRoleService.saveList(role,admin1.getId());
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"保存失败");
        }

    }

    @Override
    @Transactional //开启事务
    public void updateByEntity(AdminVo admin) {
        Admin admin1 = new Admin();
        BeanUtils.copyProperties(admin,admin1);
        List<String> role = admin.getRole();
        baseMapper.updateById(admin1);
        adminRoleService.deleteList(admin.getId());
        adminRoleService.saveList(role,admin1.getId());
    }

    @Override
    public Admin selectByAccount(String account) {
        try {
            QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account",account);
            Admin admin = baseMapper.selectOne(queryWrapper);
            return admin;
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"失败");
        }
    }

    @Override
    public AdminVo selectInfoByAccount(String account) {
        try {
            QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account",account);
            Admin admin = baseMapper.selectOne(queryWrapper);
            AdminVo adminVo = new AdminVo();
            BeanUtils.copyProperties(admin,adminVo);
            ArrayList<String> authors = new ArrayList<>();
            List<Role> roles = adminRoleService.selectRoleList(admin.getId());
            for (int i = 0; i < roles.size(); i++) {
                String roleName=roles.get(i).getName();
                authors.add(roleName);
                String rid = roles.get(i).getId();
                List<String> list = permissionService.selectPermissionByRole(rid);
                for (int i1 = 0; i1 < list.size(); i1++) {
                    String pid = list.get(i1);
                    Permission permission = permissionService.getById(pid);
                    String permissionName = permission.getName();

                    authors.add(permissionName);
                }
            }
            adminVo.setRole(authors);
            return adminVo;


        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"失败");
        }
    }


}
