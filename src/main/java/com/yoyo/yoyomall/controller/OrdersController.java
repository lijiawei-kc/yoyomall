package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.entity.Orders;
import com.yoyo.yoyomall.service.OrdersService;
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
@RequestMapping("/manager/orders")
@CrossOrigin  //跨域
public class OrdersController {

    @Autowired
    private OrdersService orderService;

    @PostMapping("/save")
    //添加订单
    public R insert(Orders orders){
        R response;
        try {
            orderService.insert(orders);
            response=R.ok();
        }catch (Exception e){
            response=R.error().msg("添加订单失败");
        }
        return response;
    }

    //删除订单
    @PostMapping("/delete")
    public R delete(String id){
        R response;
        try {
            orderService.delete(id);
            response=R.ok();
        }catch (Exception e){
            response=R.error().msg("删除订单失败");
        }
        return response;
    }
    //修改订单
    @PostMapping("/update")
    public R update(Orders orders){
        R response;
        try {
            orderService.update(orders);
            response=R.ok();
        }catch (Exception e){
            response=R.error().msg("修改订单失败");
        }
        return response;
    }

    //根据id查询订单信息
    @GetMapping("/select")
    public R selectById(String id){
        R response;
        try {
            Orders orders = orderService.selectById(id);
            System.out.println(orders);
            response=R.ok().data("orders",orders);
        }catch (Exception e){
            response=R.error().msg("查询订单信息失败");
        }
        return response;
    }

    //分页查询订单列表
    @GetMapping("/selectAll")
    public R selectAll(String uName,String gName,Integer currentPage,Integer pageSize){
        R response;
        try {
            List<Orders> ordersList = orderService.selectAll(uName,gName,currentPage, pageSize);
            Integer total = orderService.count(uName, gName);
            System.out.println(total+"total");
            System.out.println(ordersList+"orderList");
            response=R.ok().data("ordersList",ordersList).data("total",total);
        }catch (Exception e){
            response=R.error().msg("查询订单列表失败");
        }
        return response;
    }

}

