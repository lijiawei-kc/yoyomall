package com.yoyo.yoyomall.controller;
import com.yoyo.yoyomall.entity.Permission;
import com.yoyo.yoyomall.entity.vo.PermissionVo;
import com.yoyo.yoyomall.service.PermissionService;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@RestController
@RequestMapping("/manager/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 添加权限
     *
     * @param permission
     * @return R
     */
    @PostMapping("/add")
    public R insert(@RequestBody Permission permission) {
        try {
            permissionService.insert(permission);
            return R.ok().msg("添加权限成功");
        } catch (Exception e) {
            return R.error().msg("添加权限失败");

        }
    }

    /**
     * @param id 权限id
     * @return 子权限树形列表
     */
    @RequestMapping("/childrenTree")
    public R childrenTree(String id) {
        try {
            List<PermissionVo> childrenTree = permissionService.permissionTree(id);
            return R.ok().data("childrenTree", childrenTree);
        } catch (Exception e) {
            return R.error().msg("查询子权限失败");

        }
    }

    /**
     * @param id 删除权限的id
     * @return
     */
    @RequestMapping("/deleteById")
    public R delete(String id) {
        try {
            permissionService.deleteById(id);
            return R.ok().msg("删除权限列表成功");
        } catch (Exception e) {
            return R.error().msg("删除权限列表出错");
        }
    }

    /**
     * @param id 角色id
     * @return R.data中存放的是查询角色拥有的权限id
     */
    @RequestMapping("/rolePermission")
    public R rolePermission(String id) {
        try {
            List<String> pid = permissionService.selectPermissionByRole(id);
            return R.ok().data("pid", pid);
        } catch (Exception e) {
            return R.error().msg("通过角色查询权限出错");
        }
    }

    /**
     * @param permission id必须要,其他不传则不改动
     * @return
     */
    @RequestMapping("/updateById")
    public R updateById(@RequestBody Permission permission) {
      try {
          Integer rows = permissionService.upadatePermission(permission);
          if (rows==0){
              return R.error().msg("更新权限失败");
          }
          return R.ok().msg("更新权限成功");
      }catch (Exception e){
          return  R.error().msg("更新权限出错");
      }

    }
    @RequestMapping("/findById")
    public    R findById(String id){
        try {
            Permission permission = permissionService.findById(id);
            if(permission ==null){
                return R.error().msg("权限不存在");
            }
            return R.ok().data("permission",permission);
        }
        catch (Exception e){
            return  R.error().msg("查询权限出错");
        }

    }

    @GetMapping("/selectAll")
    public  R selectAll(){
        try {
            List<Permission> permissionList = permissionService.selectAll();
            return R.ok().data("permissionList",permissionList);
        }
        catch (Exception e){
            return  R.error().msg("查询权限列表出错");
        }

    }

}


