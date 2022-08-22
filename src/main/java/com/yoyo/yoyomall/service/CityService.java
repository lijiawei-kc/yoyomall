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
     R getAllProvince();
     R get8Id(Integer id);
     R get8Pid(Integer id);
}
