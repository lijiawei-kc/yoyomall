package com.yoyo.yoyomall.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Goods对象", description="商品表")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键(商品id)")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "商品描述")
    private String des;

    @ApiModelProperty(value = "商品原价(元)")
    private BigDecimal originPrice;

    @ApiModelProperty(value = "商品现价(元)")
    private BigDecimal currentPrice;

    @ApiModelProperty(value = "封面地址")
    private String imageUrl;

    @ApiModelProperty(value = "商品详情(参数信息)")
    private String info;

    @ApiModelProperty(value = "状态(未上架,已上架,已售完,已封禁...)")
    private Integer status;

    @ApiModelProperty(value = "库存数量")
    private Long stock;

    @ApiModelProperty(value = "商品创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "商品修改日期")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModify;

    @ApiModelProperty(value = "购买数量")
    private Long buyNum;

    @ApiModelProperty(value = "浏览数量")
    private Long viewNum;


}
