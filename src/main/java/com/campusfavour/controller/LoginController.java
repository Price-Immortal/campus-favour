package com.campusfavour.controller;

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
    HttpServletRequest request;
    @Autowired
    ILoginService iLoginService;

    /*
    * 登录
    * */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Map token(String userName, String password) {

        Map resultMap = new HashMap();

        Map paramMap = new HashMap();
        paramMap.put("userName",userName);
        if (iLoginService.getUserByParam(paramMap) == null) {
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
                String accessToken= TokenUtils.createJwtToken(userName);

                HttpSession session = request.getSession();
                session.setAttribute(userName,accessToken);

                resultMap.put("rtnCode","1");
                resultMap.put("rtnMsg","登陆成功");
                resultMap.put("token",accessToken);
                return resultMap;
            }
        }
    }

}
