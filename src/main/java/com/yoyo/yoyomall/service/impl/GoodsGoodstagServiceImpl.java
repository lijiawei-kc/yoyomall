package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.GoodsGoodstag;
import com.yoyo.yoyomall.mapper.GoodsGoodstagMapper;
import com.yoyo.yoyomall.service.GoodsGoodstagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.utils.YoyoException;
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
public class GoodsGoodstagServiceImpl extends ServiceImpl<GoodsGoodstagMapper, GoodsGoodstag> implements GoodsGoodstagService {

    @Override
    public void deleteList(String id) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("gid",id);
            baseMapper.delete(queryWrapper);
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"删除失败");
        }

    }



    @Override
    public void saveList(List<String> id,String gid) {
        try {
            for (int i = 0; i < id.size(); i++) {
                String tid = id.get(i);
                GoodsGoodstag goodsGoodstag = new GoodsGoodstag();
                goodsGoodstag.setGid(gid);
                goodsGoodstag.setTid(tid);
                baseMapper.insert(goodsGoodstag);
            }


        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"保存失败");
        }
    }
}
