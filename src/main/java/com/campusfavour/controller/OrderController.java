package com.campusfavour.controller;

import com.campusfavour.service.IOrderService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 订单控制器
* */
@RestController
@RequestMapping("/order")
public class OrderController extends CommonController{

    @Autowired
    private IOrderService orderService;

    /*
    * 根据类型查询发布中的订单
    * */
    //TODO 按照时间排序
    @PostMapping("/selectOrdersByParam")
    @ResponseBody
    public Map selectOrdersByParam(@RequestBody Map map){
       return orderService.selectOrdersByParam(map);
    }

    /*
     * 发布订单
     * */
    @PostMapping("/releaseOrder")
    @ResponseBody
    public Map releaseOrder(@RequestBody Map map){
        return orderService.releaseOrder(map);
    }
}
