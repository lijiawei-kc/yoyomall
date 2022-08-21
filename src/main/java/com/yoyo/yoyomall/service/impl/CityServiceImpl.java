package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.mapper.CityMapper;
import com.yoyo.yoyomall.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 市 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {
@Autowired
CityMapper cityMapper;
    @Override
    public R getAllProvince() {
        List<City> list=cityMapper.selectList(null);
        return R.ok().data("cityList",list);
    }

    @Override
    public R get8Id(Integer id) {
        City city=cityMapper.selectById(id);
        return R.ok().data("city",city);
    }

    @Override
    public R get8Pid(Integer id) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id",id);
        List<City> list=cityMapper.selectList(queryWrapper);
        return R.ok().data("cityList",list);
    }
}
