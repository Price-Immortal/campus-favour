package com.campusfavour.service;

import com.campusfavour.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IOrderService {
    //根据类型查询发布中的任务
    Map selectOrdersByParam(Map map);
    //发布任务
    Map releaseOrder(Map map);
    //修改任务
    Map updateOrder(Map map);
    //接受任务
    Map acceptOrder(Map map);
    //我的发单/我的接单
    Map usersOrders(Map map);
    //完成任务
    Map completeOrder(Map map);
}
