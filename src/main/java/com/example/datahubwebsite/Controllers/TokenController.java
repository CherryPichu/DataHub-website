package com.example.datahubwebsite.Controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

public class TokenController {

//    @Value("${jwt.password}") // application.proerties 에서 가져올 수 있다.
    private static String secretKey = "TokenPassword";
    // 참고 https://veneas.tistory.com/entry/Spring-Boot-JWT-JSON-Web-Token-토큰-기반-인증


//    //==토큰 생성 메소드==//
//    public static String createToken(String subject) {
//        Date now = new Date();
//        Date expiration = new Date(now.getTime() + Duration.ofDays(365).toMillis()); // 만료기간 1년
//
//        return Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
//                .setIssuer("test") // 토큰발급자(iss)
//                .setIssuedAt(now) // 발급시간(iat)
//                .setExpiration(expiration) // 만료시간(exp)
//                .setSubject(subject) //  토큰 제목(subject)
//                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())) // 알고리즘, 시크릿 키
//                .compact();
//    }
//
//    //==Jwt 토큰의 유효성 체크 메소드==//
//    public static Claims parseJwtToken(String token) {
//        token = token.substring("Bearer ".length());; // Bearer 제거
//        return Jwts.parser()
//                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
//                .parseClaimsJws(token)
//                .getBody();
//    }

    // 15개 랜덤 문자를 반환
    public static String createToken(String subject) {
        String token = BCrypt.gensalt(15);
        
        return token;
    }


}

