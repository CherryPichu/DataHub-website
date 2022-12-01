package com.example.datahubwebsite.Models.DAO;

import com.example.datahubwebsite.Models.DTO.Profile;
import com.example.datahubwebsite.Models.DTO.User;
import com.example.datahubwebsite.Models.Mapper.ProfileMapper;
import com.example.datahubwebsite.Models.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ProfileDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Profile profile){ // test 완료

        String sql = "Insert Into `Member.profile` (user_no, nickname, email, sex) values (?,?,?)";

        jdbcTemplate.update(sql, profile.getUser_no(), profile.getNickname(), profile.getNickname(), profile.getEmail(), profile.getSex());


    }

    public Profile readbyUserNo(int userNo){// test 완료

        String sql = "select * from `Member.profile` WHERE user_no = ?";

        Profile profile = jdbcTemplate.queryForObject(sql, new ProfileMapper(), userNo);

        return profile;
    }

    public void updatebyUserNo(Profile profile){// test 미완료

        String sql = "Update `Member.profile` SET user_no = ?,nickname = ? , email = ? , sex = ? where user_no = ?";
        jdbcTemplate.update(sql, profile.getUser_no(), profile.getNickname(), profile.getEmail(), profile.getSex(), profile.getUser_no() );

    }

    public void deletebyUserNo(int userNo){ // test 미완료
        String sql = "delete from `Member.profile` WHERE user_no = ?";
        jdbcTemplate.update(sql, userNo);

    }

}
