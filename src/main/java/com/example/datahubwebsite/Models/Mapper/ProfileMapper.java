package com.example.datahubwebsite.Models.Mapper;

import com.example.datahubwebsite.Models.DTO.Profile;
import com.example.datahubwebsite.Models.DTO.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMapper  implements RowMapper<Profile> {

    @Override
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {

        Profile profile = new Profile();
        profile.setSex(rs.getInt("sex"));
        profile.setEmail(rs.getString("email"));
        profile.setUser_no(rs.getInt("user_no"));
        profile.setNickname(rs.getString("nickname"));

        return profile;
    }
}
