package com.example.datahubwebsite.Models.Mapper;

import com.example.datahubwebsite.Models.DTO.Password;
import com.example.datahubwebsite.Models.DTO.Profile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordMapper  implements RowMapper<Password> {

    @Override
    public Password mapRow(ResultSet rs, int rowNum) throws SQLException {
        Password password = new Password();
        password.setPassword(rs.getString("Password"));
        password.setUser_no(rs.getInt("user_no"));
        password.setNickname(rs.getString("nickname"));


        return password;
    }
}
