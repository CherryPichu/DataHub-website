package com.example.datahubwebsite.Models.DTO;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Password {
    private int password_id;
    private int user_no;
    private String password;
    private String nickname;
    private String updateAt;
    private Long id;

    /**
     * Password 유저 정보
     * password_id 인덱싱
     * @param user_no 유저 아이디 - 외래키
     * @param password 암호화된 패스워드
     * @param nickname 아이디 (로그인 용도)
     */
    public Password(int user_no , String password, String nickname){
        this.user_no = user_no;
        this.password = password;
        this.nickname = nickname;
    }



}
