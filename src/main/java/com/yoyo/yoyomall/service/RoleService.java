package com.yoyo.yoyomall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yoyo.yoyomall.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-22
 */
public interface RoleService extends IService<Role> {
    public Integer insert(Role role);
    public Integer update(Role role);
    public Integer delete(String id);

    public Role selectById(String id);
    public List<Role> selectAll(String des,Integer currentPage, Integer pageSize);

    public Integer count(String des);

    public List<Role> selectAllList();
}
