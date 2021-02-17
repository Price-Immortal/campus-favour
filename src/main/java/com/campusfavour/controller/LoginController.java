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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(value = "/getToken")
    @ResponseBody
    public void test(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String token = (String)session.getAttribute("token");
        System.out.println(token);
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public Map token(String userName, String password, String token, HttpServletRequest request) {

        Map resultMap = new HashMap();

        if (iLoginService.getUserByUserName(userName) == null) {
            resultMap.put("rtnCode","0");
            resultMap.put("rtnMsg","账号不存在");
            return resultMap;
        } else {
            User result = null;

            User u = new User();
            u.setUserName(userName);
            u.setPassword(password);
            result = iLoginService.login(u);

            if (result == null) {
                resultMap.put("rtnCode","-1");
                resultMap.put("rtnMsg","密码错误");
                return resultMap;
            } else {
                //生成token
                //String accessToken= TokenUtils.createJwtToken(userName);
                String accessToken = userName;
                HttpSession session = request.getSession();
                session.setAttribute("token",accessToken);
                resultMap.put("rtnCode","1");
                resultMap.put("rtnMsg","密码错误");
                resultMap.put("token",accessToken);
                return resultMap;
            }
        }
    }
}
