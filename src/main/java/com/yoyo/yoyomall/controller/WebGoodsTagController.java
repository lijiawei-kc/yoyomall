package com.yoyo.yoyomall.controller;

import com.yoyo.yoyomall.entity.Goodstag;
import com.yoyo.yoyomall.service.GoodsService;
import com.yoyo.yoyomall.service.GoodstagService;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/goodstag")
@CrossOrigin  //跨域
public class WebGoodsTagController {
    @Autowired
    private GoodstagService goodstagService;
    @RequestMapping("/getAll")
    public R getAll(){
        List<Goodstag> goodstagList = goodstagService.getAll();
        try {
            return  R.ok().msg("获取商品标签列表成功").data("goodstagList",goodstagList);
        }catch (Exception e){
            return  R.error().msg("获取标签列表失败");
        }
    }
}
