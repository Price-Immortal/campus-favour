package com.campusfavour.controller;

import com.campusfavour.entity.User;
import com.campusfavour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
    * 当月任务排行榜
    * */
    @RequestMapping(value = "/selectTaskCountMonthRanking",method = RequestMethod.POST)
    @ResponseBody
    public List<User> selectTaskCountMonthRanking(){
        return userService.selectTaskCountMonthRanking();
    }
}
