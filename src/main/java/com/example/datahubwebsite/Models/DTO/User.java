package com.example.datahubwebsite.Models.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class) // JSON print를 위해서 사용
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

}
