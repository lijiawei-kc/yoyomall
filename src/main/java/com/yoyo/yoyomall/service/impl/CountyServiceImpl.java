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
import com.yoyo.yoyomall.utils.MathUtil;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
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

    @Override
    public R save(String name, String cid) {

        QueryWrapper<County> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        County county=countyMapper.selectOne(queryWrapper);
        if(county!=null){
            return R.error().msg("县(区)已存在");
        }

        City city=cityMapper.selectById(cid);
        if(city==null){
            return R.error().msg("市不存在");
        }





        List<County> list= countyMapper.selectList(null);
        List<String> stringList=new ArrayList<>();
        for (County county1:list) {
            stringList.add(county1.getId());
        }
        System.out.println("xx");
        MathUtil mathUtil=new MathUtil();

        Integer id=mathUtil.findMaxId(stringList)+1;
       County newCounty=new County();
       newCounty.setId(String.valueOf(id));
       newCounty.setName(name);
       newCounty.setCId(cid);
        int i=countyMapper.insert(newCounty);
        return i==1?R.ok().msg("插入成功"):R.error().msg("插入失败");
    }


}
