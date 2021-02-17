package com.campusfavour.serviceTest;

import com.campusfavour.service.IOrderService;
import com.campusfavour.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ServiceTest {

    @Autowired
    private  IUserService userService;
    @Autowired
    private IOrderService orderService;

    @Test
    public void testuser(){
        userService.selectTaskCountMonthRanking();
    }

    @Test
    public void testorder(){
        HashMap<String, String> map = new HashMap<>();
        map.put("orderType","3");
        Map map1 = orderService.selectOrdersByParam(map);
        System.out.println(map1);
    }
}
