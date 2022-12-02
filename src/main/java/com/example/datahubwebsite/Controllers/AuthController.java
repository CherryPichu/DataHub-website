package com.example.datahubwebsite.Controllers;

import com.example.datahubwebsite.Models.DAO.PasswordDao;
import com.example.datahubwebsite.Models.DAO.ProfileDao;
import com.example.datahubwebsite.Models.DAO.UserDao;
import com.example.datahubwebsite.Models.DTO.Password;
import com.example.datahubwebsite.Models.DTO.Profile;
import com.example.datahubwebsite.Models.DTO.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {


    @Autowired
    private UserDao userdb;
    @Autowired
    private PasswordDao passworddb;

    @Autowired
    private ProfileDao profiledb;


    /**
     * 
     * @param request 세션
     * @param model
     * @param AuthNickname 아디디 (로그인 용도)
     * @param password 패스워드
     * @param MemberNickname 별명 (별칭 )
     * @param email 이메일
     * @param sex 성별
     * @return
     */
    @PostMapping(value="/signUp")
    public String SignUp(HttpServletRequest request, Model model,
                         @RequestParam(value = "AuthNickname") String AuthNickname, @RequestParam(value ="password") String password,
                         @RequestParam(value = "MemberNickname") String MemberNickname, @RequestParam(value ="email") String email,
                         @RequestParam(value ="sex") int sex, @RequestParam(value="user_name") String user_name ){
        HttpSession session = request.getSession();
        

        // 토큰 생성
        String token = TokenController.createToken("사용자인증토큰");

        /**
         * 유저 테이블에 유저 정보 저장
         */
        User user = new User(user_name, 0, token);
        userdb.create(user);
        user = userdb.readbyToken(token); // 만들어진 유저 정보를 가져옴
        /**
         * 테이블 생성이 비동기 쓰레드 방시으로 동작할 가능성이 있어서
         * 테이블 생성보다 조회가 더 빠를 경우 문제가 생길 경우가 있음.
         * 보고 나중에 수정 바람.
         */

        // 세션에 user_no 저장
        session.setAttribute("user_no",user.getUser_no());


        /**
         * Auth.password 테이블에 새로운 유저 넣음.
         */
        Password newPassword = new Password(user.getUser_no() ,password, AuthNickname);
        passworddb.create( newPassword );

        /**
         * Member.profile 테이블에 profile 데이터 넣음.
         * 
         */
        Profile newProfile = new Profile( user.getUser_no(), MemberNickname, email, sex );
        profiledb.create( newProfile );


        return "";
    }

    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, Model model, @RequestParam(value = "id") int value, @RequestParam(value ="password") int password){
        HttpSession session = request.getSession();
//        session.setAttribute("user_no", 5);  // 세션 저장하기

        return "";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
//        session.setAttribute("user_no", 5);  // 세션 저장하기

        return "";
    }




}
