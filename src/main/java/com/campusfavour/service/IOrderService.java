package com.campusfavour.service;

import com.campusfavour.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IOrderService {
    //根据类型查询发布中的订单
    Map selectOrdersByParam(Map map);
    //发布订单
    Map releaseOrder(Map map);
}
