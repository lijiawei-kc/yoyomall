package com.yoyo.yoyomall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 县
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="County对象", description="县")
public class County implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "县id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "县名称")
    private String name;

    @ApiModelProperty(value = "市区id")
    private String cId;


}
