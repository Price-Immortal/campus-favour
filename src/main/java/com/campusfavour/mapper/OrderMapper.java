package com.campusfavour.mapper;

import com.campusfavour.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Mapper
@Repository
public interface OrderMapper {
    //查询发布中订单
    List<Order> selectOrdersByParam(Map map);
}
