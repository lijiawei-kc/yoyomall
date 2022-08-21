package com.yoyo.yoyomall.mapper;

import com.yoyo.yoyomall.entity.Goodstag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface GoodstagMapper extends BaseMapper<Goodstag> {
//  Goodstag getAll();

    Goodstag Test(@Param("id") String id);
}
