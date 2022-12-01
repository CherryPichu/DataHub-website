package com.example.datahubwebsite.Controllers;

import com.example.datahubwebsite.Models.DAO.PasswordDao;
import com.example.datahubwebsite.Models.DAO.ProfileDao;
import com.example.datahubwebsite.Models.DAO.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private UserDao userdb;
    @Autowired
    private PasswordDao passworddb;
    @Autowired
    private ProfileDao profiledb;

    @GetMapping(value = "/auth/login")
    public String login(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
//        session.setAttribute("user_no", 5);  // 세션 저장하기



        return "";
    }

}
