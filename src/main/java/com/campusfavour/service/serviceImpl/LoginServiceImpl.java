package com.campusfavour.service.serviceImpl;

import com.campusfavour.entity.User;
import com.campusfavour.mapper.UserMapper;
import com.campusfavour.service.LoginService;
import com.campusfavour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserAccount(String userAccount) {
        return userMapper.getUserByUserAccount(userAccount);
    }
}
