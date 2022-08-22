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
@RequestMapping("/web/orders")
@CrossOrigin  //跨域
public class WebOrdersController {

    @Autowired
    private OrdersService orderService;

    @PostMapping("/createOrder")
    //生成订单
    public R insert(Orders orders){
        R response;
        try {
            orderService.insert(orders);
            response=R.ok();
        }catch (Exception e){
            response=R.error().msg("生成订单失败");
        }
        return response;
    }

    //订单信息完成
    @PostMapping("/save")
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
    @GetMapping("/info")
    public R selectById(String id){
        R response;
        try {
            Orders orders = orderService.selectById(id);
            response=R.ok().data("orders",orders);
        }catch (Exception e){
            response=R.error().msg("查询订单信息失败");
        }
        return response;
    }

    //查看用户所有订单列表
    @GetMapping("/selectAll")
    public R selectAllList(String uId,String gName,Integer currentPage,Integer pageSize){
        R response;
        try {
            List<Orders> ordersList = orderService.selectAllList(uId,gName,currentPage, pageSize);
            Integer total = orderService.countByUid(uId,gName);
            response=R.ok().data("ordersList",ordersList).data("total",total);
        }catch (Exception e){
            response=R.error().msg("查询订单列表失败");
        }
        return response;
    }

    //生成支付页面
    @GetMapping("/createPay")
    public R createPay(String id){
        R response = null;

        return response;
    }
}

