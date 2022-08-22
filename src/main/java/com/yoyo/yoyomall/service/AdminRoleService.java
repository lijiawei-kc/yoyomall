package com.yoyo.yoyomall.service;

import com.yoyo.yoyomall.entity.AdminRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author serol
 * @since 2022-08-22
 */
public interface AdminRoleService extends IService<AdminRole> {

    void saveList(List<String> role, String id);

    List<String> selectList(String id);

    void deleteList(String id);

}
