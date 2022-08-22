package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.entity.Province;
import com.yoyo.yoyomall.mapper.CityMapper;
import com.yoyo.yoyomall.mapper.CountyMapper;
import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.service.CountyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 县 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@Service
public class CountyServiceImpl extends ServiceImpl<CountyMapper, County> implements CountyService {
    @Autowired
    CountyMapper countyMapper;
    @Autowired
    CityMapper cityMapper;
    @Autowired
    ProvinceMapper provinceMapper;


    @Override
    public R getAllProvince() {
        List<County> list=countyMapper.selectList(null);
        return R.ok().data("countyList",list);
    }

    @Override
    public R get8Id(Integer id) {
        County county= countyMapper.selectById(id);
        return R.ok().data("county",county);
    }

    @Override
    public R get8Cid(Integer id) {
        QueryWrapper<County> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_id",id);
        List<County> list=countyMapper.selectList(queryWrapper);
        return R.ok().data("countryList",list);
    }

    public  R getAdr8Id(Integer id){
        County county= countyMapper.selectById(id);
        City city=cityMapper.selectById(county.getCId());
        Province province=provinceMapper.selectById(city.getPId());
        Map<String,Object> map= new HashMap<>();
        map.put("province",province.getName());
        map.put("city",city.getName());
        map.put("county",county.getName());
        return R.ok().data(map);
    }


}
