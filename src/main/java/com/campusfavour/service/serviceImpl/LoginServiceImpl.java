package com.campusfavour.service.serviceImpl;

import com.campusfavour.entity.User;
import com.campusfavour.mapper.UserMapper;
import com.campusfavour.service.ILoginService;
import com.campusfavour.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByParam(Map map) {
        return userMapper.getUserByParam(map);
    }

    @Override
    public User login(User user) {
        Map paramMap = new HashMap();
        paramMap.put("userName",user.getUserName());
        User userData = userMapper.getUserByParam(paramMap);
        if ( userData.getPassword().equals(user.getPassword()) ){
            return userData;
        }else{
            return null;
        }
    }
}
