package com.yoyo.yoyomall.service.impl;

import com.yoyo.yoyomall.entity.Goodstag;
import com.yoyo.yoyomall.service.GoodstagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest

public class GoodstagServiceImplTest {
@Autowired
private GoodstagService goodstagService;
    @Test
    public void findById() {
        Goodstag goodstag = goodstagService.findById("1");
        System.out.println("++++"+goodstag+"++++");
    }
    @Test
    public  void  getAll(){
        System.out.println(goodstagService.getAll());
    }
    @Test
    public  void  insert(){
        Goodstag goodstag = new Goodstag();
        goodstag.setName("智能电视");
        goodstagService.insert(goodstag);

    }
    @Test
    public  void deleteById(){
        goodstagService.deleteById("1");
    }
    @Test
    public  void findByName(){
        Goodstag goodstag = goodstagService.findByName("add");
        System.out.println(goodstag);
    }
    @Test
    public  void  updateById(){
        Goodstag goodstag = new Goodstag();
        goodstag.setId("1561267138395574273");
        goodstag.setName("阿巴巴");
        goodstagService.update(goodstag);
        
    }

}