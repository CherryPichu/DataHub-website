package com.example.datahubwebsite.Models.Mapper;

import com.example.datahubwebsite.Models.DTO.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUser_name(rs.getString("name"));
        user.setUser_no(rs.getInt("userNo"));
        user.setToken(rs.getString("Token"));
        user.setUser_name(rs.getString("userName"));

        return user;
    }
}
