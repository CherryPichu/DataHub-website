package com.example.datahubwebsite.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Profile {
    private int profile_id;
    private int user_no;// 외래키
    private String nickname; // 별칭
    private String email; // 이메일
    private int sex; // 0 = "male" , 1 = "female";
    private String createAt;
    private String updateAt;

    /**
     *
     * profile_id 인덱싱
     * @param user_no 유저 번호 - 외래키
     * @param nickname ㅂ려칭
     * @param email 이메일
     * @param sex 성별
     *  createAt  생성 날짜 - sql query 문
     *  updateAt  마지막 업데이트 날짜 - sql query 문
     */
    public Profile(int user_no, String nickname, String email, int sex) {
        this.user_no = user_no;
        this.nickname = nickname;
        this.email = email;
        this.sex = sex;
    }

}
