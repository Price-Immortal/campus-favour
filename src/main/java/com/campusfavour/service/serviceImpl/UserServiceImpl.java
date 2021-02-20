package com.campusfavour.service.serviceImpl;

import com.campusfavour.entity.Order;
import com.campusfavour.entity.User;
import com.campusfavour.mapper.UserMapper;
import com.campusfavour.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map selectTaskCountRanking() {
        HashMap<String, Object> returnMap = new HashMap<>();
        List<User> userMaps = userMapper.selectTaskCountRanking();
        returnMap.put("beans",userMaps);
        returnMap.put("rtnCode","1");
        returnMap.put("rtnMsg","selectTaskCountRanking查询成功");
        return returnMap;
    }

    @Override
    public Map selectTaskCountMonthRanking() {
        HashMap<String, Object> returnMap = new HashMap<>();
        List<User> userMaps = userMapper.selectTaskCountMonthRanking();
        returnMap.put("beans",userMaps);
        returnMap.put("rtnCode","1");
        returnMap.put("rtnMsg","selectTaskCountMonthRanking查询成功");
        return returnMap;
    }

    @Override
    public void countMonthTask() {
        userMapper.countMonthTask();
    }

    @Override
    public List<Map> selectUserIdList() {
        return userMapper.selectUserList();
    }

    @Override
    public void creditScoreTask() {
        List<Map> userLists = selectUserIdList();
        for (Map user : userLists) {
            if ( (int)user.get("creditScore") <= 80){
                //正常添加的
                user.put("creditScore",(int)user.get("creditScore")+20);
                userMapper.creditScoreTask(user);
            }else{
                //直接设为100的
                user.put("creditScore",100);
                userMapper.creditScoreTask(user);
            }
        }
    }

    @Override
    public Map registerUser(Map map) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        map.put("createTime",new Date());
        userMapper.registerUser(map);
        resultMap.put("rtnCode","1");
        resultMap.put("rtnMsg","注册成功");
        return resultMap;
    }
}
