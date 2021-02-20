package com.campusfavour.service.serviceImpl;

import com.campusfavour.entity.Order;
import com.campusfavour.entity.User;
import com.campusfavour.mapper.OrderMapper;
import com.campusfavour.mapper.UserMapper;
import com.campusfavour.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

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
        //判断用户是否可以发布任务,信誉分80分以下不能发布任务
        String releaseUserId = (String)map.get("releaseUserId");
        Map paramMap = new HashMap();
        paramMap.put("id",releaseUserId);
        User user = userMapper.getUserByParam(paramMap);

        if ( user.getCreditScore() >= 80){
            orderMapper.releaseOrder(map);
            returnMap.put("rtnCode","1");
            returnMap.put("rtnMsg","releaseOrder发布任务成功");
            return returnMap;
        }else{
            returnMap.put("rtnCode","-1");
            returnMap.put("rtnMsg","信誉分不足80，无法发布任务");
            return returnMap;
        }
    }

    /*
    * 接受任务
    * */
    @Override
    public Map acceptOrder(Map map) {
        Map returnMap = new HashMap();
        //判断用户是否可以接受任务,信誉分60分以下不能接受任务
        String receiveUserId = (String)map.get("receiveUserId");
        Map paramMap = new HashMap();
        paramMap.put("id",receiveUserId);
        User user = userMapper.getUserByParam(paramMap);
        if ( user.getCreditScore() >= 60){
            orderMapper.acceptOrder(map);
            returnMap.put("rtnCode","1");
            returnMap.put("rtnMsg","acceptOrder接受任务成功");
            return returnMap;
        }else{
            returnMap.put("rtnCode","-1");
            returnMap.put("rtnMsg","信誉分不足60，无法接受任务");
            return returnMap;
        }
    }
}
