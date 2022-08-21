package com.yoyo.yoyomall.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodstagMapperTest {
@Autowired
private  GoodstagMapper goodstagMapper;
    @Test
    public void getById() {
        System.out.println(goodstagMapper.Test("1561267053502861313"));
    }
}