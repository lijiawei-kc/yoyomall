package com.yoyo.yoyomall.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yoyo.yoyomall.entity.City;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CityVo  {
    @ApiModelProperty(value = "市区id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "市区名称")
    private String name;

    @ApiModelProperty(value = "省份id")
    private String pId;

    @ApiModelProperty(value = "省份名")
    private  String pName;
}
