package com.campusfavour.mapper;

import com.campusfavour.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Mapper
@Repository
public interface OrderMapper {
    //查询发布中任务
    List<Order> selectOrdersByParam(Map map);
    //发布任务
    void releaseOrder(Map map);
    //接受任务
    void acceptOrder(Map map);
    //我的发单
    List<Order> userRelease(Map map);
}
