package com.yoyo.yoyomall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yoyo.yoyomall.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yoyo.yoyomall.entity.vo.GoodsQueryVo;
import com.yoyo.yoyomall.entity.vo.GoodsVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */

public interface GoodsService extends IService<Goods> {

    Goods selectById(Integer id);



    List<Goods> selectAll(String page, String limit, GoodsQueryVo goodsQueryVo);

    void save(GoodsVo goods);

    void updateById(GoodsVo goods);

    void deleteById(String id);
}
