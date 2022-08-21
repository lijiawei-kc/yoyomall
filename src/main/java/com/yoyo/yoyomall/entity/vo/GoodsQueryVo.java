package com.yoyo.yoyomall.entity.vo;


import lombok.Data;

@Data
public class GoodsQueryVo {
    private String title;         //商品名称
    private String pricelow;        //最低价
    private String pricehigh;      //最高价
    private String status;       //商品状态
    private String num;      //库存
    private String ispricedesc;     //是否价格降序
    private String startTime;       //起始时间
    private String endTime;       //截至时间
    private String tid;


}
