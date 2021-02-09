package com.campusfavour.controller;

import com.campusfavour.entity.User;
import com.campusfavour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends CommonController{

    @Autowired
    private UserService userService;

    /*
     * 总任务排行榜
     * */
    @GetMapping("/selectTaskCountRanking")
    @ResponseBody
    public Map selectTaskCountRanking(){
        return userService.selectTaskCountRanking();
    }

    /*
    * 当月任务排行榜
    * */
    @GetMapping("/selectTaskCountMonthRanking")
    @ResponseBody
    public Map selectTaskCountMonthRanking(){
        return userService.selectTaskCountMonthRanking();
    }

}
