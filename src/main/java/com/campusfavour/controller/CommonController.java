package com.campusfavour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
//解决跨域问题
@CrossOrigin(origins = "http://127.0.0.1:5500",allowCredentials = "true")
@Controller
public class CommonController {
}
