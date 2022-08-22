package com.yoyo.yoyomall.service.impl;

import com.yoyo.yoyomall.entity.Permission;
import com.yoyo.yoyomall.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class PermissionServiceImplTest {
@Autowired
private PermissionService permissionService;
    @Test
    public void permissionTree() {
        System.out.println(permissionService.permissionTree("1561528004475043842"));
    }
    @Test
    public  void  delete(){
        permissionService.deleteById("55");
    }
    @Test
    public  void  upadteById(){
        Permission permission = new Permission();
        permission.setId("15616104056258");
        permission.setName("最高权限1");

        permissionService.upadatePermission(permission);
    }
}