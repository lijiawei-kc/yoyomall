package com.yoyo.yoyomall.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CountyVo {
    @ApiModelProperty(value = "县id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "县名称")
    private String name;

    @ApiModelProperty(value = "市区id")
    private String cId;
    @ApiModelProperty(value="市区名")
    private String cname;
}
