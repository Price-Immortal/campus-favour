package com.campusfavour.mapper;

import com.campusfavour.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    //查询用户列表
    List<Map> selectUserList();
    //登录-通过参数查询用户信息
    User getUserByParam(Map paramMap);
    //任务完成数总榜单列表查询
    List<User> selectTaskCountRanking();
    //任务完成数当月榜单列表查询
    List<User> selectTaskCountMonthRanking();
    void updateUserById(Map map);
    //定时任务-每月刷新用户月任务完成数
    void countMonthTask();
    //定时任务-每月增加用户信誉分20分
    void creditScoreTask(Map users);
    //注册
    void registerUser(Map map);
    //月完成数加1
    void monthNumAdd(Map map);
    //总完成数加1
    void totalNumAdd(Map map);
}
