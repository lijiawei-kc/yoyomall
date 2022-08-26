package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.yoyo.yoyomall.entity.User;
import com.yoyo.yoyomall.mapper.UserMapper;
import com.yoyo.yoyomall.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author serol
 * @since 2022-08-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public Integer insert(User user) {
        int row = baseMapper.insert(user);
        return row;
    }

    @Override
    public Integer delete(String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        int row = baseMapper.delete(queryWrapper);
        return row;
    }

    @Override
    public Integer update(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",user.getId());
        int row = baseMapper.update(user,queryWrapper);
        return row;
    }


    @Override
    public List<User> selectAll(String uName, Integer currentPage, Integer pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.apply(!StringUtils.isNullOrEmpty(uName),"match(nickname) against ('"+uName+"')");
        Page<User> page = new Page<>(currentPage,pageSize);

        return this.page(page, queryWrapper).getRecords();
    }

    @Override
    public User selectByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("nickname",name);
        User user = baseMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User selectById(String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        User user = baseMapper.selectOne(queryWrapper);
        return user;
    }
}
