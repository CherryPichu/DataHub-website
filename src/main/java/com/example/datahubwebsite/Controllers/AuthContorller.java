package com.example.datahubwebsite.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthContorller {
    @PostMapping(value="/Auth/login")
    public String Loginmethod(Model model, @RequestParam(value="id", required = true) String id,
                              @RequestParam(value="id", required = true) String password){
        // 로그인 세션 생성
        return "success";
    }

    @PostMapping(value="/Auth/logout")
    public String Loginmethod(Model model){
        // 세션 지우기
        return "success";
    }



}
