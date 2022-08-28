package com.yoyo.yoyomall.service;

import com.yoyo.yoyomall.entity.Goodstag;
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
public interface GoodstagService extends IService<Goodstag> {
      Goodstag findById(String id);
        List<Goodstag>getAll();
        Integer insert(Goodstag goodstag);
       Integer deleteById(String id);
       Goodstag findByName(String name);
      Integer update (Goodstag goodstag);
      List<Goodstag> findByNameGoodsTagList(String name);

}
