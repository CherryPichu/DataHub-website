package com.example.datahubwebsite.Controllers;

import com.example.datahubwebsite.Models.DAO.UserDao;
import com.example.datahubwebsite.Models.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@Controller
@RestController
@SessionAttributes("user_no") // model.addAttribute("id", id)
// spring 세션 : https://memory-develo.tistory.com/147
public class TestContorller {
    @Autowired
    private UserDao userdb;

    @GetMapping("/Auth/test")
    public String test(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        session.setAttribute("user_no", 5);  // 세션 저장하기
        
        
        model.addAttribute("user_no", 5); // 세션값 저장.
        User user = new User("namjung2",0, "12345");
        userdb.create(user);

        return ";";
    }

    @GetMapping("/Auth/test2")
    public String test2(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();

        User user;
        user = userdb.readbyUserNo(4);

        return session.getAttribute("user_no").toString();
    }

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
