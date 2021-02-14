package com.campusfavour.controller;

import com.campusfavour.annotation.CurrentUser;
import com.campusfavour.annotation.LoginRequired;
import com.campusfavour.entity.User;
import com.campusfavour.service.ILoginService;
import com.campusfavour.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController extends CommonController {

    @Autowired
    ILoginService iLoginService;

    @GetMapping("/setCookie")
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("test","same");
        cookie.setPath("/");
        response.addCookie(cookie);
        return "success";
    }

    @GetMapping("/getCookie")
    @ResponseBody
    public String getCookie(HttpServletRequest request, HttpServletResponse response){
        System.out.println("2222222222222222");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length >0) {
            for (Cookie cookie : cookies) {
                System.out.println("name:" + cookie.getName() + "-----value:" + cookie.getValue());
            }
        }
        return "success";
    }

    @LoginRequired
    @RequestMapping(value = "/token")
    public String token(@CurrentUser User user, String account, String token) {

        /*log.info(account+"----"+token);
        log.info("----"+user.getUserName());
        log.info("params==" + user.toString());*/
        if (iLoginService.getUserByUserName(account) == null) {
            return "账号不存在";
        } else {
            User result = null;
            result = iLoginService.login(user);
            //生成token
            String accessToken= TokenUtils.createJwtToken(user.getUserName());
            if (result == null) {
                return  "密码错误";
            } else {
                return "SUCCESS";
            }
        }
    }
}
