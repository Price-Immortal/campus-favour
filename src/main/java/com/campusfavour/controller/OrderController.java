package com.campusfavour.controller;

import com.campusfavour.annotation.CurrentUser;
import com.campusfavour.annotation.LoginRequired;
import com.campusfavour.entity.User;
import com.campusfavour.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
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
    * 根据类型查询发布中的任务
    * */
    //TODO 按照时间排序
    @PostMapping("/selectOrdersByParam")
    @ResponseBody
    public Map selectOrdersByParam(@RequestBody Map map){
       return orderService.selectOrdersByParam(map);
    }

    /*
     * 发布任务
     * */
    @LoginRequired
    @PostMapping("/releaseOrder")
    @ResponseBody
    public Map releaseOrder(@RequestBody Map map, @CurrentUser User user){
        //任务发布人
        map.put("releaseUserName",user.getUserName());
        //任务发布人id
        map.put("releaseUserId",user.getUserId());
        //任务发布时间
        map.put("releaseTime",new Date());

        return orderService.releaseOrder(map);
    }

    /*
    * 修改任务
    * */
    @LoginRequired
    @PostMapping("/updateOrder")
    @ResponseBody
    public Map updateOrder(@RequestBody Map map, @CurrentUser User user){

        return orderService.updateOrder(map);
    }

    /*
    *接受任务
    * */
    @LoginRequired
    @PostMapping("/acceptOrder")
    @ResponseBody
    public Map acceptOrder(@RequestBody Map map,@CurrentUser User user){
        //任务接单人
        map.put("receiveUserName",user.getUserName());
        //任务接单人id
        map.put("receiveUserId",user.getUserId());
        //任务接单时间
        map.put("receiveTime",new Date());

        return  orderService.acceptOrder(map);
    }

     /*
     * 取消任务
     * */
    @LoginRequired
    @PostMapping("/cancelOrderFromReceive")
    @ResponseBody
    public Map cancelOrderReceive(@RequestBody Map map,@CurrentUser User user){
        map.put("userId",user.getUserId());
        return orderService.cancelOrder(map);
    }

    /*
    * 我的发单
    * */
    @LoginRequired
    @PostMapping("/userRelease")
    @ResponseBody
    public Map userRelease(@CurrentUser User user){
        Map paramMap = new HashMap();
        paramMap.put("releaseUserId",user.getUserId());
        return orderService.usersOrders(paramMap);
    }

    /*
    * 我的接单
    * */
    @LoginRequired
    @PostMapping("/userReceive")
    @ResponseBody
    public Map userReceive(@CurrentUser User user){
        Map paramMap = new HashMap();
        paramMap.put("receiveUserId",user.getUserId());
        return orderService.usersOrders(paramMap);
    }

    /*
    * 完成任务
    * */
    @LoginRequired
    @PostMapping("/completeOrder")
    @ResponseBody
    public Map completeOrder(@RequestBody Map map,@CurrentUser User user){
        map.put("userId",user.getUserId());
        return orderService.completeOrder(map);
    }
}
