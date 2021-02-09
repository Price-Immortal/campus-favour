package com.campusfavour.service.serviceImpl;

import com.campusfavour.entity.Order;
import com.campusfavour.mapper.OrderMapper;
import com.campusfavour.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Map selectOrdersByParam(Map map) {
        HashMap<String, Object> returnMap = new HashMap<>();
        List<Order> orders = orderMapper.selectOrdersByParam(map);
        returnMap.put("beans",orders);
        returnMap.put("rtnCode","1");
        returnMap.put("rtnMsg","selectOrdersByParam查询成功");
        return returnMap;

    }
}
