package com.yoyo.yoyomall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.yoyo.yoyomall.entity.Orders;
import com.yoyo.yoyomall.mapper.OrdersMapper;
import com.yoyo.yoyomall.service.CountyService;
import com.yoyo.yoyomall.service.OrdersService;
import com.yoyo.yoyomall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-21
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private CountyService countyService;

    @Override
    public Integer insert(Orders orders) {
        int row = baseMapper.insert(orders);
        return row;
    }

    @Override
    public Integer update( Orders orders) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",orders.getId());
        int row = baseMapper.update(orders,updateWrapper);
        return row;
    }

    @Override
    public Integer delete(String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        int row = baseMapper.delete(queryWrapper);
        return row;
    }

    @Override
    public Orders selectById(String id) {
        Orders orders = baseMapper.selectById(id);
        orders.setReceivingAddress(getAddress(orders.getCoid())+orders.getAddress());
        return orders;
    }

    @Override
    public List<Orders> selectAll(String uName,String gName,Integer currentPage, Integer pageSize) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper();
        queryWrapper.apply(!StringUtils.isNullOrEmpty(uName),"match(u_name) against ('"+uName+"')");
        queryWrapper.apply(!StringUtils.isNullOrEmpty(gName),"match(g_name) against ('"+gName+"')");
        Page<Orders> page = new Page<>(currentPage,pageSize);

        List<Orders> ordersList = this.page(page, queryWrapper).getRecords();
        for (Orders orders : ordersList) {
            orders.setReceivingAddress(getAddress(orders.getCoid())+orders.getAddress());
        }
        return ordersList;
    }

    //查询总条数
    @Override
    public Integer count(String uName,String gName) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper();
        queryWrapper.apply(!StringUtils.isNullOrEmpty(uName),"match(u_name) against ('"+uName+"')");
        queryWrapper.apply(!StringUtils.isNullOrEmpty(gName),"match(g_name) against ('"+gName+"')");
        Integer count = baseMapper.selectCount(queryWrapper);
        return count;
    }

    @Override
    public List<Orders> selectAllList(String uId, String gName, Integer currentPage, Integer pageSize) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper();
        queryWrapper.eq(!StringUtils.isNullOrEmpty(uId), "u_id", uId);
        queryWrapper.apply(!StringUtils.isNullOrEmpty(gName), "match(g_name) against ('" + gName + "')");
        Page<Orders> page = new Page<>(currentPage, pageSize);

        List<Orders> ordersList = this.page(page, queryWrapper).getRecords();
        for (Orders orders : ordersList) {
            orders.setReceivingAddress(getAddress(orders.getCoid())+orders.getAddress());
        }
        return ordersList;
    }
    @Override
    public Integer countByUid(String uId, String gName) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper();
        queryWrapper.eq(!StringUtils.isNullOrEmpty(uId),"u_id",uId);
        queryWrapper.apply(!StringUtils.isNullOrEmpty(gName),"match(g_name) against ('"+gName+"')");
        Integer count = baseMapper.selectCount(queryWrapper);
        return count;
    }

    public String getAddress(String coid){
        R addressResp = countyService.getAdr8Id(Integer.valueOf(coid));
        Map<String, Object> addressRespData = addressResp.getData();
        String address = (String)addressRespData.get("province")+addressRespData.get("city")+addressRespData.get("county");
        return address;
    }
}
