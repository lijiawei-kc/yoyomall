package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.entity.Province;
import com.yoyo.yoyomall.entity.vo.CountyVo;
import com.yoyo.yoyomall.mapper.CityMapper;
import com.yoyo.yoyomall.mapper.CountyMapper;
import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.service.CountyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.utils.MathUtil;
import com.yoyo.yoyomall.utils.R;
import com.yoyo.yoyomall.utils.YoyoException;
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
        List<CountyVo> listVo=new ArrayList<>();
        for (County c:list) {
            City city=cityMapper.selectById(c.getCId());
            CountyVo countyVo=new CountyVo();
            countyVo.setId(c.getId());
            countyVo.setName(c.getName());
            countyVo.setCId(c.getCId());
            countyVo.setCname(city.getName());
            listVo.add(countyVo);
        }

        return R.ok().data("countyList",listVo);
    }

    @Override
    public R get8Id(String id) {
        County county= countyMapper.selectById(id);
        City city=cityMapper.selectById(county.getCId());
        CountyVo countyVo=new CountyVo();
        countyVo.setId(county.getId());
        countyVo.setName(county.getName());
        countyVo.setCId(county.getCId());
        countyVo.setCname(city.getName());
        return R.ok().data("county",countyVo);
    }

    @Override
    public R get8Cid(String id) {
        QueryWrapper<County> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_id",id);
        List<County> list=countyMapper.selectList(queryWrapper);
        City city=cityMapper.selectById(id);
        List<CountyVo> listVo=new ArrayList<>();
        for (County county:list) {
            CountyVo countyVo=new CountyVo();
            countyVo.setId(county.getId());
            countyVo.setName(county.getName());
            countyVo.setCId(county.getCId());
            countyVo.setCname(city.getName());
            listVo.add(countyVo);
        }
        return R.ok().data("countyList",listVo);
    }

    public  R getAdr8Id(String id){
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
    public R save(String name, String cname) {

        QueryWrapper<County> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        County county;
        try {
            county=countyMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw  new YoyoException(20001,"查询县区错误");
        }
        if(county!=null){
            return R.error().msg("县(区)已存在");
        }

        QueryWrapper<City> cityQueryWrapper=new QueryWrapper<>();
        cityQueryWrapper.eq("name",cname);
        City city;
        try {
            city=cityMapper.selectOne(cityQueryWrapper);
        }catch (Exception e){
            throw new YoyoException(20001,"查询市错误");
        }
        if(city==null){
            return R.error().msg("市不存在");
        }


        List<County> list= countyMapper.selectList(null);
        List<String> stringList=new ArrayList<>();
        for (County county1:list) {
            stringList.add(county1.getId());
        }

        MathUtil mathUtil=new MathUtil();
        Integer id=mathUtil.findMaxId(stringList)+1;

       County newCounty=new County();
       newCounty.setId(String.valueOf(id));
       newCounty.setName(name);
       newCounty.setCId(city.getId());
        int i=countyMapper.insert(newCounty);
        return i==1?R.ok().msg("插入成功"):R.error().msg("插入失败");
    }

    @Override
    public R update(String name, String id,String cname) {
        County county;
        try {
            county=countyMapper.selectById(id);
        }catch (Exception e){
            throw new YoyoException(20001,"查询县时出错");
        }

        if(county==null) return R.error().msg("该县id不存在");

        QueryWrapper<City> cityQueryWrapper=new QueryWrapper<>();
        cityQueryWrapper.eq("name",cname);
        City city;
        try {
            city=cityMapper.selectOne(cityQueryWrapper);
        }catch (Exception e){
            throw new YoyoException(20001,"查询所属市时出错");
        }

        if(city==null) return R.error().msg("添加所属市不存在");

        County newCounty=new County();
        newCounty.setId(id);
        newCounty.setCId(city.getId());
        newCounty.setName(name);

        int i;
        try{
            i=countyMapper.updateById(newCounty);
        }catch (Exception e){
            e.printStackTrace();
            return R.error().msg("修改错误");
        }
        return i==1?R.ok().msg("修改成功"):R.error().msg("修改失败");
    }

    @Override
    public R delete(String id) {
        int i;
        try {
             i=countyMapper.deleteById(id);
        }catch (Exception e){
            return R.error().msg("删除错误");
        }
        return i==1?R.ok().msg("删除成功"):R.error().msg("删除失败");
    }

    @Override
    public R selectByName(String name) {
        QueryWrapper<County> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        County county;
       try {
           county=countyMapper.selectOne(queryWrapper);
       }catch (Exception e){
           throw new YoyoException(20001,"查询异常");
       }
       if(county==null) return R.error().msg("没有该县");

       City city=cityMapper.selectById(county.getCId());

        CountyVo countyVo=new CountyVo();
        countyVo.setId(county.getId());
        countyVo.setName(county.getName());
        countyVo.setCId(county.getCId());
        countyVo.setCname(city.getName());

        return R.ok().data("county",countyVo);


    }

    @Override
    public R selectByCname(String cname) {
        QueryWrapper<City> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",cname);
        City city;
        try {
             city=cityMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw new YoyoException(20001,"查询市名出错");
        }
        if(city==null) return R.error().msg("市名不存在");

        return get8Cid(city.getId());

    }


}
