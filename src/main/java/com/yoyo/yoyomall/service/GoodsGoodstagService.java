package com.yoyo.yoyomall.service;

import com.yoyo.yoyomall.entity.GoodsGoodstag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
public interface GoodsGoodstagService extends IService<GoodsGoodstag> {

    void deleteList(String id);

    void saveList(List<String> id,String gid);
}
