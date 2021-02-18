package com.campusfavour.service.serviceImpl;

import com.campusfavour.entity.User;
import com.campusfavour.mapper.UserMapper;
import com.campusfavour.service.ILoginService;
import com.campusfavour.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public User login(User user) {
        User userData = userMapper.getUserByUserName(user.getUserName());
        if ( userData.getPassword().equals(user.getPassword()) ){
            return userData;
        }else{
            return null;
        }
    }
}
