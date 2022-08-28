package com.yoyo.yoyomall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yoyo.yoyomall.entity.City;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yoyo.yoyomall.utils.R;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>
 * 市 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
public interface CityService extends IService<City> {
     R getAllCity();
     R get8Id(String id);
     R get8Pid(String id);

     R save(String name,String pname);

     R update(String name,String pid,String id);

     R delete(String id);

     R get8Name(String name);

     R get8Pname(String pname);


}
