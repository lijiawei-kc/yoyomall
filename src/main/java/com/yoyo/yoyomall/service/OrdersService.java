package com.yoyo.yoyomall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yoyo.yoyomall.entity.Orders;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
public interface OrdersService extends IService<Orders> {
    public Integer insert(Orders orders);
    public Integer update( Orders orders);
    public Integer delete(String id);

    public Orders selectById(String id);
    public List<Orders> selectAll(String uName,String gName,Integer currentPage, Integer pageSize);

    public Integer count(String uName,String gName);

    List<Orders> selectAllList(String uId, String gName, Integer currentPage, Integer pageSize);

    Integer countByUid(String uId, String gName);
}
