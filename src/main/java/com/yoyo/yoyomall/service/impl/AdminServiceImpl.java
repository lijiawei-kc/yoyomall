package com.yoyo.yoyomall.service.impl;

import com.yoyo.yoyomall.entity.Admin;
import com.yoyo.yoyomall.mapper.AdminMapper;
import com.yoyo.yoyomall.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
   public void test(){
       Admin admin;
       admin = new Admin();
       admin.setAccount("tset");
       admin.setPassword("test");
       admin.setAvatar("test");
       baseMapper.insert(admin);
   }


}
