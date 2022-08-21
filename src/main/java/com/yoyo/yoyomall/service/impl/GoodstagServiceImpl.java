package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.Goodstag;
import com.yoyo.yoyomall.mapper.GoodstagMapper;
import com.yoyo.yoyomall.service.GoodstagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@Service
public class GoodstagServiceImpl extends ServiceImpl<GoodstagMapper, Goodstag> implements GoodstagService {

    @Autowired
    private  GoodstagMapper goodstagMapper;
     public  Goodstag findById(String id){

         Goodstag goodstag = baseMapper.selectById(id);
         return goodstag;
     }



    @Override
    public List<Goodstag> getAll() {
        QueryWrapper queryWrapper = new QueryWrapper();

       try {
           List<Goodstag> goodstagList = baseMapper.selectList(queryWrapper);
           return goodstagList;
       }catch (Exception e){
           e.printStackTrace();
           return  null;

       }
    }

    @Override
    public Integer insert(Goodstag goodstag) {
        int rows = baseMapper.insert(goodstag);
        return rows;
    }

    @Override
    public Integer deleteById(String id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public Goodstag findByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",name);
        Goodstag goodstag = baseMapper.selectOne(queryWrapper);
        return  goodstag;
    }

    @Override
    public Integer update(Goodstag goodstag) {
        int rows = baseMapper.updateById(goodstag);
        return rows;
    }


}
