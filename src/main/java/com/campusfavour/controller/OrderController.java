package com.campusfavour.controller;

import com.campusfavour.annotation.CurrentUser;
import com.campusfavour.annotation.LoginRequired;
import com.campusfavour.entity.User;
import com.campusfavour.service.IOrderService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        map.put("releaseUserId",user.getId());
        //任务发布时间
        map.put("releaseTime",new Date());

        return orderService.releaseOrder(map);
    }

    /*
    *接受任务
    * */
    @LoginRequired
    @PostMapping("/releaseOrder")
    @ResponseBody
    public Map acceptOrder(@RequestBody Map map,@CurrentUser User user){
        Map returnMap = new HashMap();
        //任务接单人
        map.put("receiveUserName",user.getUserName());
        //任务接单人id
        map.put("receiveUserId",user.getId());
        //任务接单时间
        map.put("receiveTime",new Date());
        orderService.acceptOrder(map);
        return returnMap;
    }
}
