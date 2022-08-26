package com.yoyo.yoyomall.service.impl;

import com.yoyo.yoyomall.entity.Admin;
import com.yoyo.yoyomall.entity.Role;
import com.yoyo.yoyomall.entity.vo.AdminVo;
import com.yoyo.yoyomall.service.AdminRoleService;
import com.yoyo.yoyomall.service.AdminService;
import com.yoyo.yoyomall.service.PermissionService;
import com.yoyo.yoyomall.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private PermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
//       Admin admin =  adminService.selectByAccount(account);
//        List<String> ridlist = adminRoleService.selectList(admin.getId());
//        HashSet<String> pidList = new HashSet<>();
//        for (int i = 0; i < ridlist.size(); i++) {
//            String rid = ridlist.get(i);
//            List<String> list = permissionService.selectPermissionByRole(rid);
//            for (int i1 = 0; i1 < list.size(); i1++) {
//                String pid = list.get(i);
//                pidList.add(pid);
//            }
//        }
        AdminVo adminVo = adminService.selectInfoByAccount(account);

//
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = adminVo.getRole();
        for (int i = 0; i < roles.size(); i++) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.get(i));
            authorities.add(authority);
        }
//
        if(adminVo==null)
            throw new UsernameNotFoundException("请检查你的账号!");

        User user = new User(adminVo.getAccount(),adminVo.getPassword(),authorities);
        return user;
    }
}
