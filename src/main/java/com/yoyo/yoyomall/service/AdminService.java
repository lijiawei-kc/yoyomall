package com.yoyo.yoyomall.service;

import com.yoyo.yoyomall.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yoyo.yoyomall.entity.vo.AdminVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-20
 */

public interface AdminService extends IService<Admin> {
    public void test();

    void deleteById(String id);

    void insert(AdminVo admin);

    void updateByEntity(AdminVo admin);

    Admin selectByAccount(String account);

    AdminVo selectInfoByAccount(String account);

    List<AdminVo> selectAll();
}
