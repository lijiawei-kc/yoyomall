package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.entity.Province;
import com.yoyo.yoyomall.entity.vo.CityVo;
import com.yoyo.yoyomall.mapper.CityMapper;
import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.service.ProvinceService;
import com.yoyo.yoyomall.utils.MathUtil;
import com.yoyo.yoyomall.utils.R;
import com.yoyo.yoyomall.utils.YoyoException;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Autowired
    ProvinceMapper provinceMapper;
    @Override
    public R getAllCity() {
        List<City> list=cityMapper.selectList(null);
        List<CityVo> listVo=new ArrayList<>();
        for (City c:list) {
            Province province=provinceMapper.selectById(c.getPId());
            CityVo cityVo=new CityVo();
            cityVo.setName(c.getName());
            cityVo.setId(c.getId());
            cityVo.setPId(c.getPId());
            cityVo.setPName(province.getName());
            listVo.add(cityVo);
        }
        return R.ok().data("cityList",listVo);
    }

    @Override
    public R get8Id(String id) {
        City city=cityMapper.selectById(id);
        Province province=provinceMapper.selectById(city.getPId());
        CityVo cityVo=new CityVo();
        cityVo.setName(city.getName());
        cityVo.setId(city.getId());
        cityVo.setPId(city.getPId());
        cityVo.setPName(province.getName());
        return R.ok().data("city",cityVo);
    }

    @Override
    public R get8Pid(String id) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id",id);
        List<City> list=cityMapper.selectList(queryWrapper);
        List<CityVo> listVo=new ArrayList<>();
        for (City c:list) {
            Province province=provinceMapper.selectById(c.getPId());
            CityVo cityVo=new CityVo();
            cityVo.setName(c.getName());
            cityVo.setId(c.getId());
            cityVo.setPId(c.getPId());
            cityVo.setPName(province.getName());
            listVo.add(cityVo);
        }
        return R.ok().data("cityList",listVo);
    }

    @Override
    public R save(String name, String pname) {

            QueryWrapper<City> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",name);
            City city=cityMapper.selectOne(queryWrapper);
            if(city!=null){
                return R.error().msg("市已存在");
            }

            QueryWrapper<Province> provinceQueryWrapper=new QueryWrapper<>();
            provinceQueryWrapper.eq("name",pname);
             Province province=provinceMapper.selectOne(provinceQueryWrapper);
            if(province==null){
                return R.error().msg("省不存在");
            }
            String pid=province.getId();





            List<City> list= cityMapper.selectList(null);
            List<String> stringList=new ArrayList<>();
            for (City city1:list) {
                stringList.add(city1.getId());
            }

            MathUtil mathUtil=new MathUtil();

            Integer id=mathUtil.findMaxId(stringList)+1;
            City newCity=new City();
            newCity.setId(String.valueOf(id));
            newCity.setName(name);
            newCity.setPId(String.valueOf(pid));

            int i=cityMapper.insert(newCity);
            return i==1?R.ok().msg("插入成功"):R.error().msg("插入失败");


    }

    @Override
    public R update(String name, String id,String pname) {
        City city;
        try{
            city=cityMapper.selectById(id);
        }catch (Exception e){
            throw new YoyoException(20001,"查询市id时出错");
        }

        if(city==null) return R.error().msg("该市id不存在");


        QueryWrapper<Province> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",pname);
        Province province;
       try{
           province=provinceMapper.selectOne(queryWrapper);
       }catch (Exception e){
           throw new YoyoException(20001,"查询省名时出错");
       }
        if(province==null) return R.error().msg("添加所属省不存在");

        City newCity=new City();
        newCity.setId(id);
        newCity.setPId(province.getId());
        newCity.setName(name);

        int i;

        try{
            i=cityMapper.updateById(newCity);
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
            i=cityMapper.deleteById(id);
        }catch (Exception e){
            return R.error().msg("删除错误,请检查该市下的县是否删完");
        }
        return i==1?R.ok().msg("删除成功"):R.error().msg("删除失败");
    }

    @Override
    public R get8Name(String name) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        City city;
        try {
            city=cityMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw new YoyoException(20001,"查询异常");
        }
        if(city==null){return R.error().msg("没有该市名");}
        Province province=provinceMapper.selectById(city.getPId());
        CityVo cityVo=new CityVo();
        cityVo.setName(city.getName());
        cityVo.setId(city.getId());
        cityVo.setPId(city.getPId());
        cityVo.setPName(province.getName());
        return R.ok().data("city",cityVo);
    }

    @Override
    public R get8Pname(String pname) {
        QueryWrapper<Province> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",pname);
        Province province;
        try{
           province= provinceMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw new YoyoException(20001,"查询出错");
        }
        if(province==null){
            throw new YoyoException(20001,"省不存在");
        }
        return get8Pid(province.getId());
    }


}
