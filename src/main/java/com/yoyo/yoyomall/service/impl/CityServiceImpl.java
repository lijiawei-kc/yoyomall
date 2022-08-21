package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.entity.County;
import com.yoyo.yoyomall.entity.Province;
import com.yoyo.yoyomall.mapper.CityMapper;
import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.service.ProvinceService;
import com.yoyo.yoyomall.utils.MathUtil;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
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
@Autowired
    ProvinceMapper provinceMapper;
    @Override
    public R getAllProvince() {
        List<City> list=cityMapper.selectList(null);
        return R.ok().data("cityList",list);
    }

    @Override
    public R get8Id(String id) {
        City city=cityMapper.selectById(id);
        return R.ok().data("city",city);
    }

    @Override
    public R get8Pid(String id) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id",id);
        List<City> list=cityMapper.selectList(queryWrapper);
        return R.ok().data("cityList",list);
    }

    @Override
    public R save(String name, String pid) {

            QueryWrapper<City> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",name);
            City city=cityMapper.selectOne(queryWrapper);
            if(city!=null){
                return R.error().msg("市已存在");
            }

            Province province=provinceMapper.selectById(pid);
            if(province==null){
                return R.error().msg("省不存在");
            }





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
    public R update(String name, String pid, String id) {
        City city=cityMapper.selectById(id);
        if(city==null) return R.error().msg("该市id不存在");

        Province province=provinceMapper.selectById(pid);
        if(province==null) return R.error().msg("添加所属省不存在");

        City newCity=new City();
        newCity.setId(id);
        newCity.setPId(pid);
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
            return R.error().msg("删除错误");
        }
        return i==1?R.ok().msg("删除成功"):R.error().msg("删除失败");
    }


}
