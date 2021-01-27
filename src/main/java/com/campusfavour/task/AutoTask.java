package com.campusfavour.task;

import com.campusfavour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@EnableScheduling-开启定时任务
@EnableScheduling
@Component
public class AutoTask {

    @Autowired
    private UserService userService;

    /*
     *每月1号将用户上月完成任务总数清0
     * */
    // @Scheduled-定时器  时间表达式(每月1号刷新数据库月数据)
    @Scheduled(cron="0 0 0 1 * ?")
    private void countMonthTask(){
        userService.countMonthTask();
    }

    /*
     *每月1号将用户信誉分增加20分
     * */
    // @Scheduled-定时器  时间表达式(每月1号刷新数据库月数据)
    @Scheduled(cron="0 0 0 1 * ?")
    private void creditScoreTask(){
        userService.creditScoreTask();
    }
}
