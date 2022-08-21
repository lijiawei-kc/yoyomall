package com.yoyo.yoyomall.service;

import com.yoyo.yoyomall.entity.Province;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yoyo.yoyomall.utils.R;

/**
 * <p>
 * 省 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
public interface ProvinceService extends IService<Province> {
     R getAllProvince();

     R get8Id(String id);
      R save(String name);

      R update(String id,String name);

      R delete(String id);

}
