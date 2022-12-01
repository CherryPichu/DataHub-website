package com.example.datahubwebsite.Models.Mapper;

import com.example.datahubwebsite.Models.DTO.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUser_name(rs.getString("user_name"));
        user.setUser_no(rs.getInt("user_no"));
        user.setToken(rs.getString("token"));
        user.setLogin_type(rs.getInt("login_type"));

        return user;
    }
}
