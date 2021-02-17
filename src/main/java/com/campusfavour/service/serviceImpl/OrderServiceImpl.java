package com.campusfavour.service.serviceImpl;

import com.campusfavour.entity.Order;
import com.campusfavour.mapper.OrderMapper;
import com.campusfavour.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    /*
    * 根据类型查询发布中的订单
    * */
    @Override
    public Map selectOrdersByParam(Map map) {
        HashMap<String, Object> returnMap = new HashMap<>();
        List<Order> orders = orderMapper.selectOrdersByParam(map);
        returnMap.put("beans",orders);
        returnMap.put("rtnCode","1");
        returnMap.put("rtnMsg","selectOrdersByParam查询成功");
        return returnMap;
    }

    /*
    * 发布订单
    * */
    @Override
    public Map releaseOrder(Map map) {
        HashMap<String, Object> returnMap = new HashMap<>();

        //任务发布人
        map.put("releaseUserName","");
        //任务发布人id
        map.put("releaseUserId","");

        orderMapper.releaseOrder(map);
        returnMap.put("rtnCode","1");
        returnMap.put("rtnMsg","releaseOrder发布订单成功");
        return returnMap;
    }
}
