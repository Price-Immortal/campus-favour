package com.campusfavour.mapper;

import com.campusfavour.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //登录-通过用户名查询用户信息
    User getUserByName(String name);
    //任务完成数当月榜单列表查询
    List<User> selectTaskCountMonthRanking();
}
