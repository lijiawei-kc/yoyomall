package com.yoyo.yoyomall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yoyo.yoyomall.entity.Goods;
import com.yoyo.yoyomall.entity.vo.GoodsQueryVo;
import com.yoyo.yoyomall.entity.vo.GoodsVo;
import com.yoyo.yoyomall.service.GoodsGoodstagService;
import com.yoyo.yoyomall.service.GoodsService;
import com.yoyo.yoyomall.utils.R;
import com.yoyo.yoyomall.utils.YoyoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private GoodsGoodstagService goodstagService;

    @GetMapping("/select/{id}")
    public R selectById(@PathVariable Integer id){
      Goods goods= goodsService.selectById(id);
        return R.ok().data("goods",goods);
    }

    @PostMapping("/save")
    public R save(GoodsVo goods){
        System.out.println(goods);
        try {
            goodsService.save(goods);
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"保存失败");
        }

        return R.ok();
    }
    @PostMapping("/delete")
    public R deleteById(String id){
        try {
            goodsService.deleteById(id);

        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"删除失败");
        }

        return R.ok();
    }
    @PostMapping("/update")
    public R update(GoodsVo goods){
        try {
            goodsService.updateById(goods);
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"保存失败");
        }

        return R.ok();
    }
    @PostMapping("/selectall/{page}/{limit}")
    public R selectall(GoodsQueryVo goodsQueryVo,@PathVariable String page,@PathVariable String limit){
        try {

      List<Goods> list =    goodsService.selectAll(page,limit,goodsQueryVo);
            return R.ok().data("list",list).data("total",list.size());
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001,"保存失败");
        }


    }





}

