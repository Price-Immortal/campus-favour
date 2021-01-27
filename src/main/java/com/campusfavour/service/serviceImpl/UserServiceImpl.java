package com.campusfavour.service.serviceImpl;

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
    public List<User> selectTaskCountRanking() {
        return userMapper.selectTaskCountRanking();
    }

    @Override
    public List<User> selectTaskCountMonthRanking() {
        return userMapper.selectTaskCountMonthRanking();
    }

    @Override
    public void countMonthTask() {
        userMapper.countMonthTask();
    }

    @Override
    public List<Map> selectUserIdList() {
        return userMapper.selectUserIdList();
    }

    @Override
    public void creditScoreTask() {
        List<Map> users = selectUserIdList();
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
