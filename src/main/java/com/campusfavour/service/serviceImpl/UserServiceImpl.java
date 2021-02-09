package com.campusfavour.service.serviceImpl;

import com.campusfavour.entity.Order;
import com.campusfavour.entity.User;
import com.campusfavour.mapper.UserMapper;
import com.campusfavour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

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
    public Map selectUserIdList() {
        HashMap<String, Object> returnMap = new HashMap<>();
        List<Map> userMaps = userMapper.selectUserIdList();
        returnMap.put("beans",userMaps);
        returnMap.put("rtnCode","1");
        returnMap.put("rtnMsg","selectUserIdList查询成功");
        return returnMap;
    }

    @Override
    public void creditScoreTask() {
        Map userMaps = selectUserIdList();
        List<Map> users = (List<Map>) userMaps.get("beans");
        for (Map user : users) {
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

}
