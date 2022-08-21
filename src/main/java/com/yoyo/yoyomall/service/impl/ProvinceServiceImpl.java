package com.yoyo.yoyomall.service.impl;

import com.yoyo.yoyomall.entity.Province;
import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.service.ProvinceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 省 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {

    @Autowired
    ProvinceMapper provinceMapper;
    @Override
    public R getAllProvince() {
        List<Province> list=provinceMapper.selectList(null);
        return R.ok().data("provinceList",list);
    }

    @Override
    public R get8Id(Integer id) {
        Province province= provinceMapper.selectById(id);
        return R.ok().data("province",province);
    }
}
