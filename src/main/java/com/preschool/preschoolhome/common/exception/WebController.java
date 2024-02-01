package com.preschool.preschoolhome.common.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController implements ErrorController {
    // 1. 이 방법
    // 2. 404 만 캐치해서 index.html
    // 3. /* , /*/* , /*/*/*, /*/*/*/* 이 주소로 왔을때 index.html
    // 4. ViewResolver => FORWARD /


    @GetMapping({"/","/error"})
    public String index(){
        return "index.html";
    }
}
