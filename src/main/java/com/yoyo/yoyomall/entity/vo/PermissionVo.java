package com.yoyo.yoyomall.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yoyo.yoyomall.entity.Permission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class PermissionVo {
    @ApiModelProperty(value = "权限id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限说明")
    private String des;

    @ApiModelProperty(value = "权限对应接口")
    private String menu;

    @ApiModelProperty(value = "父权限id")
    private String parentId;

    private List<PermissionVo> children;//所有子权限
}
