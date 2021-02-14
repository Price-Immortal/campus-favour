package com.campusfavour.service;

import com.campusfavour.entity.User;

public interface ILoginService {
    User getUserByUserName(String userName);
    User login(User user);
}
