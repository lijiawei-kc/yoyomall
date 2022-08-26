package com.yoyo.yoyomall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yoyo.yoyomall.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author serol
 * @since 2022-08-24
 */
public interface UserService extends IService<User> {
    public Integer insert(User user);
    public Integer delete(String id);
    public Integer update(User user);
    public List<User> selectAll(String uName, Integer currentPage, Integer pageSize);

    User selectByName(String name);

    User selectById(String id);
}
