package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@RestController
@RequestMapping("/manager/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;





}

