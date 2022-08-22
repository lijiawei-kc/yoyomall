package com.yoyo.yoyomall.mapper;

import com.yoyo.yoyomall.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> permmissionTree(@Param("parentId") String parentId);
    Integer deletePermission(@Param("id") String id);
    Integer deleteRolePermission(@Param("id") String id);
    List<String> selectPermissionByRole(@Param("id") String id);


}
