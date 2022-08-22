//package com.yoyo.yoyomall.service.impl;
//
//import com.yoyo.yoyomall.entity.Admin;
//import com.yoyo.yoyomall.service.AdminRoleService;
//import com.yoyo.yoyomall.service.AdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private AdminRoleService adminRoleService;
//    @Override
//    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
//       Admin admin =  adminService.selectByAccount(account);
//        List<String> list = adminRoleService.selectList(admin.getId());
//
//
//        //TODO
//        User user = new User(admin.getAccount(),admin.getPassword(),null);
//
//        return null;
//    }
//}
