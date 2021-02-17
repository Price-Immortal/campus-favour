package com.campusfavour.controller;

import com.campusfavour.annotation.LoginRequired;
import com.campusfavour.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends CommonController{

    @Autowired
    private IUserService userService;

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
//    @LoginRequired
    @GetMapping("/selectTaskCountMonthRanking")
    @ResponseBody
    public Map selectTaskCountMonthRanking(){
        return userService.selectTaskCountMonthRanking();
    }

}
