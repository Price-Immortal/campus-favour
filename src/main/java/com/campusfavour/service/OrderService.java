package com.campusfavour.service;

import com.campusfavour.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map selectOrdersByParam(Map map);
}
