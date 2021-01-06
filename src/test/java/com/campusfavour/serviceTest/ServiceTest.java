package com.campusfavour.serviceTest;

import com.campusfavour.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceTest {

    @Autowired
    private  UserService userService;

    @Test
    public void test(){
        userService.selectTaskCountMonthRanking();
    }
}
