package com.campusfavour.mapper;

import com.campusfavour.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    //查询用户id列表
    List<Map> selectUserIdList();
    //登录-通过用户名查询用户信息
    User getUserByUserAccount(String userAccount);
    //任务完成数总榜单列表查询
    List<User> selectTaskCountRanking();
    //任务完成数当月榜单列表查询
    List<User> selectTaskCountMonthRanking();
    //定时任务-每月刷新用户月任务完成数
    void countMonthTask();
    //定时任务-每月增加用户信誉分20分
    void creditScoreTask(Map users);
}
