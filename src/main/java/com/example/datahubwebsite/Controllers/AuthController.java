package com.example.datahubwebsite.Controllers;

import com.example.datahubwebsite.Controllers.TokenController;
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
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {


    @Autowired
    private UserDao userdb;
    @Autowired
    private PasswordDao passworddb;

    @Autowired
    private ProfileDao profiledb;


    /**
     * 회원가입 요청
     * 생각해봤는데 회사에서는 이딴식으로 짜면 안됨. 원자성, 회복을 고려해야함.
     * 회원가입중 실패 발생시 이전 commit 을 다 취소해야함!!!
     * 근데 이번 프로젝트는 신경쓰기 싫어서 안함.
     * @param request 세션
     * @param AuthNickname 아디디 (로그인 용도)
     * @param password 패스워드
     * @param MemberNickname 별명 (별칭 )
     * @param email 이메일
     * @param sex 성별
     * @param user_name 사용자 이름
     * @return
     */
    @PostMapping(value="/signUp")
    public String SignUp(HttpServletRequest request,
                         @RequestParam(value = "AuthNickname") String AuthNickname, @RequestParam(value ="password") String password,
                         @RequestParam(value = "MemberNickname") String MemberNickname, @RequestParam(value ="email") String email,
                         @RequestParam(value ="sex") int sex, @RequestParam(value="user_name") String user_name ){


        HttpSession session = request.getSession();
        System.out.println("파이널컷1");

        /**
         * 중복되는 아이디가 있는지 확인
         */
        if(passworddb.readbyNickName(AuthNickname) != null){
            return "error 아이디 중복";
        };

        System.out.println("파이널컷2");

        // 토큰 생성
        String token = TokenController.createToken("사용자인증토큰");

        /**
         * 유저 테이블에 유저 정보 저장
         */
        User user = new User(user_name, 0, token);
        userdb.create(user);
        System.out.println("파이널컷3");
        user = userdb.readbyToken(token); // 만들어진 유저 정보를 가져옴
        int uesr_no = user.getUser_no();
        /**
         * 테이블 생성이 비동기 쓰레드 방시으로 동작할 가능성이 있어서
         * 테이블 생성보다 조회가 더 빠를 경우 문제가 생길 경우가 있음.
         * 보고 나중에 수정 바람.
         */

        // 세션에 user_no 저장
        session.setAttribute("user_no",user.getUser_no());

        System.out.println("파이널컷4");
        /**
         * Auth.password 테이블에 새로운 유저 넣음.
         */
        String hashpw = BCrypt.hashpw( password, BCrypt.gensalt() ); // 비교시 BCrypt.checkpw(plainpw, hashpw) 사용.
        Password newPassword = new Password(uesr_no  , hashpw, AuthNickname);
        passworddb.create( newPassword );



        /**
         * Member.profile 테이블에 profile 데이터 넣음.
         *
         */
        Profile newProfile = new Profile(uesr_no , MemberNickname, email, sex );
        profiledb.create( newProfile );



        return "";
    }

    /**
     * 로그인 요청
     * @param request
     * @param nickname 로그인 아이디
     * @param password 로그인 패스워드
     * @return "sucess" 세션에 user_no 저장, "fall"
     */
    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, @RequestParam(value = "id") String nickname, @RequestParam(value ="password") String password){
        HttpSession session = request.getSession();


        Password auth = passworddb.readbyNickName(nickname);

        if(auth == null){
            throw new ForbiddenException();
        }


        if( BCrypt.checkpw(password, auth.getPassword()) ){
            session.setAttribute("user_no", auth.getUser_no());  // 세션 저장하기
            return "sucess";
        }

        throw new ForbiddenException();
//        return "fall";
    }

    /**
     * 로그아웃 요청, 세션 정보 삭제
     * @param request
     * @return 
     */
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
//        session.setAttribute("user_no", null);  // 세션 저장하기
        System.out.println("user_no 삭제 전 :  " + session.getAttribute("user_no") );
        session.removeAttribute("user_no"); // 세션 삭제
        System.out.println("user_no 삭제 후 :  " + session.getAttribute("user_no") );

        return "sucess";
    }


    /**
     * 세션으로 유저번호(user_no) 조회
     */
    @GetMapping(value = "/getuser_no")
    public String getUser_no(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        if( session.getAttribute("user_no") != null){
            return session.getAttribute("user_no").toString();
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "null";
        }

    }

    @GetMapping(value = "/getuserJson")
    public User getuserJson(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        if( session.getAttribute("user_no") != null){
            return  userdb.readbyUserNo((Integer) session.getAttribute("user_no"));
        }else {
            User errUser = new User("null", 0, -1, "");
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return errUser;
        }
    }

    /**
     * 아이디 중복 검사
     * @param request
     * @param nickname 중복 검사 대상 아이디 / targeted ID
     */
    @PostMapping(value = "/CheckedDuplicatedId")
    public String CheckedDuplicatedId(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "id") String nickname){
        HttpSession session = request.getSession();

        if(passworddb.readbyNickName(nickname) ==  null){
            return "yes";
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "null";

    }
}

