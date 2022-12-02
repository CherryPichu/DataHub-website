package com.example.datahubwebsite.Models.DTO;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String user_name;
    private int login_type;
    private int user_no;
    private String token;

    /**
     * 유저 테이블 DTO
     * @param user_name String; 유저 이름
     * @param login_type String; 유저 로그인 타입 (이번 프로젝트에서 'local' 으로 고정)
     * @param token String; 유저 토큰 정보
     */
    public User(String user_name, int login_type , String token){
        this.user_name = user_name;
        this.login_type = login_type;
        this.token = token;
    }

//    public String getUser_name() {
//        return user_name;
//    }
//
//    public void setUser_name(String user_name) {
//        this.user_name = user_name;
//    }
//
//    public int getLogin_type() {
//        return login_type;
//    }
//
//    public void setLogin_type(int login_type) {
//        this.login_type = login_type;
//    }
//
//    public int getUser_no() {
//        return user_no;
//    }
//
//    public void setUser_no(int user_no) {
//        this.user_no = user_no;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
}
