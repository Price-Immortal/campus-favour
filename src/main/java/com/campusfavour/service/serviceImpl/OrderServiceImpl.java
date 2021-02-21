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
        paramMap.put("userId",releaseUserId);
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

    @Override
    public Map updateOrder(Map map) {
        Map returnMap = new HashMap();
        orderMapper.updateOrder(map);
        returnMap.put("rtnCode","1");
        returnMap.put("rtnMsg","updateOrder更新任务成功");
        return returnMap;
    }

    /*
    * 接受任务
    * */
    @Override
    public Map acceptOrder(Map map) {
        Map returnMap = new HashMap();
        //判断用户是否可以接受任务,信誉分60分以下不能接受任务
        if ( map.get("receiveUserId") != null ){
            String receiveUserId = (String)map.get("receiveUserId");
            Map paramMap = new HashMap();
            paramMap.put("userId",receiveUserId);
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
        }else{
            returnMap.put("rtnCode","0");
            returnMap.put("rtnMsg","参数为空");
            return returnMap;
        }
    }

    /*
    * 我的发单/我的接单
    * */
    @Override
    public Map usersOrders(Map map) {
        Map returnMap = new HashMap();
        List<Order> orders = orderMapper.usersOrders(map);
        returnMap.put("beans",orders);
        returnMap.put("rtnCode","1");
        returnMap.put("rtnMsg","userRelease查询成功");
        return returnMap;
    }

    /*
    * 完成任务
    * */
    @Override
    public Map completeOrder(Map map) {
        Map returnMap = new HashMap();
        //用户当月、总完成任务数加1
        userMapper.monthNumAdd(map);
        userMapper.totalNumAdd(map);
        //任务状态变更05
        map.put("orderStatus","05");
        orderMapper.updateOrder(map);
        //用户信誉分加5
        User user = userMapper.getUserByParam(map);
        Map paramMap = new HashMap();
        paramMap.put("userId",user.getUserId());
        if ( user.getCreditScore() <= 95 ){
            paramMap.put("creditScore",user.getCreditScore()+5);
            userMapper.updateUserById(paramMap);
        }else{
            paramMap.put("creditScore",100);
            userMapper.updateUserById(paramMap);
        }
        returnMap.put("rtnCode","1");
        returnMap.put("rtnMsg","completeOrder完成任务成功");
        return returnMap;
    }

    /*
    *取消任务
    * */
    @Override
    public Map cancelOrder(Map map) {
        Map returnMap = new HashMap();
        //获取用户
        String userId = (String) map.get("userId");
        Map paramMap = new HashMap();
        paramMap.put("userId",userId);
        User user = userMapper.getUserByParam(paramMap);


        if ( map.get("orderId")!= null){

            //判断订单状态扣除信誉积分
            Map userParamMap = new HashMap();
            Map orderParamMap = new HashMap();
            String orderId = (String)map.get("orderId");
            orderParamMap.put("orderId",orderId);
            List<Order> orders = orderMapper.selectOrdersByParam(orderParamMap);
            String orderStatus = orders.get(0).getOrderStatus();
            if ( "02".equals(orderStatus) ){
                //未拿到货
                userParamMap.put("userId",user.getUserId());
                userParamMap.put("creditScore",user.getCreditScore()-3);
                userMapper.updateUserById(userParamMap);
            }else if ( "03".equals(orderStatus) ){
                //已拿到货
                userParamMap.put("userId",user.getUserId());
                userParamMap.put("creditScore",user.getCreditScore()-10);
                userMapper.updateUserById(userParamMap);
            }
            //更改订单状态为04
            orderParamMap.put("orderId",orderId);
            orderParamMap.put("orderStatus","04");
            orderMapper.updateOrder(orderParamMap);
            //是否警告
            user = userMapper.getUserByParam(paramMap);
            int creditScore = user.getCreditScore();
            if( creditScore < 60){
                returnMap.put("rtnCode","-2");
                returnMap.put("rtnMsg","您当前的信誉分为"+creditScore+",将不能接发任务，请等待次月恢复至60分以上时接单增加信誉分。");
                return returnMap;
            }else if( creditScore < 80 ){
                returnMap.put("rtnCode","-2");
                returnMap.put("rtnMsg","请注意，您当前的信誉分为"+creditScore+",低于60分将不能接发任务。");
                return returnMap;
            }else if( creditScore == 80 ){
                returnMap.put("rtnCode","-2");
                returnMap.put("rtnMsg","请注意，您当前的信誉分为"+creditScore+",低于80分将不能发布任务。");
                return returnMap;
            }
            returnMap.put("rtnCode","1");
            returnMap.put("rtnMsg","cancelOrderFromRelease取消任务-发单人 成功");
            return returnMap;
        }else{
            returnMap.put("rtnCode","0");
            returnMap.put("rtnMsg","参数为空");
            return returnMap;
        }
    }
}
