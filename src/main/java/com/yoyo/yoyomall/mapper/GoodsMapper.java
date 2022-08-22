package com.yoyo.yoyomall.mapper;

import com.yoyo.yoyomall.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yoyo.yoyomall.entity.vo.GoodsQueryVo;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    public List<Goods> selectAll(String page, String limit, GoodsQueryVo vo);

    String selectLastInsertId();
}
