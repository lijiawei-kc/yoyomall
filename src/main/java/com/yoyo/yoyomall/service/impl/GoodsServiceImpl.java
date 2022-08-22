package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.cj.util.StringUtils;
import com.yoyo.yoyomall.entity.Goods;
import com.yoyo.yoyomall.entity.vo.GoodsQueryVo;
import com.yoyo.yoyomall.entity.vo.GoodsVo;
import com.yoyo.yoyomall.mapper.GoodsMapper;
import com.yoyo.yoyomall.service.GoodsGoodstagService;
import com.yoyo.yoyomall.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoyo.yoyomall.utils.YoyoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

@Autowired
private GoodsGoodstagService goodstagService;
    @Override
    public Goods selectById(Integer id) {
        try {
            Goods goods = baseMapper.selectById(id);
            return goods;
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"查询商品异常...");
        }


    }

    @Override
    public List<Goods> selectAll(String page,String limit, GoodsQueryVo goodsQueryVo) {
        Integer p = Integer.valueOf(page)*Integer.valueOf(limit);
        page=p.toString();

        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.orderByAsc("buy_num");
        if(goodsQueryVo == null){
            List list = baseMapper.selectList(queryWrapper);
            return list;
        }
        String tid1 = goodsQueryVo.getTid();
        if(!StringUtils.isNullOrEmpty(tid1)){
            List<Goods> list = baseMapper.selectAll(page, limit, goodsQueryVo);
            return list;
        }


        //TODO
        String title = goodsQueryVo.getTitle();
        if (!StringUtils.isNullOrEmpty(title))
            queryWrapper.like("title",title);


        String startTime = goodsQueryVo.getStartTime();
        String endTime = goodsQueryVo.getEndTime();
        if(!StringUtils.isNullOrEmpty(startTime)){
            queryWrapper.ge("gmt_create",startTime);
        }
        if(!StringUtils.isNullOrEmpty(endTime)){
            queryWrapper.le("gmt_create",endTime);
        }

        String pricehigh = goodsQueryVo.getPricehigh();
        String pricelow = goodsQueryVo.getPricelow();
        if (!StringUtils.isNullOrEmpty(pricehigh))
            queryWrapper.le("current_price",pricehigh);
        if(!StringUtils.isNullOrEmpty(pricelow))
            queryWrapper.ge("current_price",pricelow);

        String num = goodsQueryVo.getNum();
        if (!StringUtils.isNullOrEmpty(num))
            queryWrapper.ge("stock",num);

        String ispricedesc = goodsQueryVo.getIspricedesc();
        if(!StringUtils.isNullOrEmpty(ispricedesc)){
            if (ispricedesc.equals("true"))
                queryWrapper.orderByDesc("price");
            if (ispricedesc.equals("false"))
                queryWrapper.orderByAsc("price");
        }

        String status = goodsQueryVo.getStatus();
        if (!StringUtils.isNullOrEmpty(status))
            queryWrapper.eq("status",status);

        queryWrapper.last(" limit "+page+","+limit);



        List<Goods> list = baseMapper.selectList(queryWrapper);
        return list;

    }

    @Override
    @Transactional //开启事务
    public void save(GoodsVo goods) {
        Goods goods1 = new Goods();
        BeanUtils.copyProperties(goods,goods1);
        this.save(goods1);
        List<String> tid = goods.getTid();
      String id =  baseMapper.selectLastInsertId();

        goodstagService.saveList(tid,id);
    }

    @Override
    @Transactional
    public void updateById(GoodsVo goods) {
        Goods goods1 = new Goods();
        BeanUtils.copyProperties(goods,goods1);


        baseMapper.updateById(goods1);
        goodstagService.deleteList(goods.getId());
        if (!StringUtils.isNullOrEmpty(goods.getTid().toString()))
        goodstagService.saveList(goods.getTid(),goods.getId());
        goodstagService.deleteList(goods.getId());

    }

    @Override
    @Transactional
    public void deleteById(String id) {
        baseMapper.deleteById(id);
        goodstagService.deleteList(id);

    }
}
