package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.entity.Province;
import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.service.ProvinceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.utils.MathUtil;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public R get8Id(String id) {
        Province province= provinceMapper.selectById(id);
        return R.ok().data("province",province);
    }
    @Override
    public R save(String name) {

        QueryWrapper<Province> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Province pro=provinceMapper.selectOne(queryWrapper);
        if(pro!=null){
            return R.error().msg("省已存在");
        }


        List<Province> list= provinceMapper.selectList(null);
        List<String> stringList=new ArrayList<>();
        for (Province province:list) {
            stringList.add(province.getId());
        }
        MathUtil mathUtil=new MathUtil();
        Integer id=mathUtil.findMaxId(stringList)+1;
        Province province=new Province();
        province.setId(String.valueOf(id));
        province.setName(name);
        int i=provinceMapper.insert(province);
        return i==1?R.ok().msg("插入成功"):R.error().msg("插入失败");
    }


}
