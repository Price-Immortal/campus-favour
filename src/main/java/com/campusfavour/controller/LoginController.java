package com.campusfavour.controller;

import com.campusfavour.entity.User;
import com.campusfavour.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/*
* 登录控制器
* */
@Controller
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/login")
    @ResponseBody
    public String login(String username,String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "请输入用户名和密码！";
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在！");
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            System.out.println("账号或密码错误");
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            System.out.println("没有权限！！");
            return "没有权限";
        }
        System.out.println("login success");
        return "login success";
    }
}
