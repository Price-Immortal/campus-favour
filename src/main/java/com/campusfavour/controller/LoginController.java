package com.campusfavour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController extends CommonController {
    @GetMapping("/setCookie")
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        System.out.println("11111111111111111111111111");
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
}
