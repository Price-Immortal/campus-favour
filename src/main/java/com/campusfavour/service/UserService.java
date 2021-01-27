package com.campusfavour.service;

import com.campusfavour.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //获取用户列表
    List<Map> selectUserIdList();
    //总任务排行榜
    List<User> selectTaskCountMonthRanking();
    //当月任务排行榜
    List<User> selectTaskCountRanking();
    //定时任务-每月刷新用户当月完成任务数
    void countMonthTask();
    //定时任务-每月增加用户信誉分20分
    void creditScoreTask();
}
