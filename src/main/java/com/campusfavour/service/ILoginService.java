package com.campusfavour.service;

import com.campusfavour.entity.User;

import java.util.Map;

public interface ILoginService {
    User getUserByParam(Map map);
    User login(User user);
}
