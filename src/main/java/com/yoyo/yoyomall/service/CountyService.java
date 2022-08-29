package com.yoyo.yoyomall.service;

import com.yoyo.yoyomall.entity.City;
import com.yoyo.yoyomall.entity.County;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yoyo.yoyomall.entity.Province;
import com.yoyo.yoyomall.mapper.CityMapper;
import com.yoyo.yoyomall.mapper.ProvinceMapper;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 县 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
public interface CountyService extends IService<County> {
     R getAllProvince();
     R get8Id(String id);
     R get8Cid(String id);

     R getAdr8Id(String id);

     R save(String name,String cname);
      R update(String name,String id,String cname);
      R delete(String id);

      R selectByName(String name);

      R selectByCname(String cname);

}
