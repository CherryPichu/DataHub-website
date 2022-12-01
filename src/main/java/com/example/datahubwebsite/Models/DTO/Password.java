package com.example.datahubwebsite.Models.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Password {
    private int password_id;
    private int user_no;
    private String password;
    private String nickname;
    private String updateAt;

    public Password(int password_id, int user_no , String password, String nickname){
        this.password_id = password_id;
        this.user_no = user_no;
        this.password = password;
        this.nickname = nickname;
    }

    public int getPassword_id() {
        return password_id;
    }

    public void setPassword_id(int password_id) {
        this.password_id = password_id;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
