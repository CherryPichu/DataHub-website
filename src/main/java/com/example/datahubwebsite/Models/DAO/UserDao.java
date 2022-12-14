package com.example.datahubwebsite.Models.DAO;

import com.example.datahubwebsite.Models.DTO.User;
import com.example.datahubwebsite.Models.Mapper.ProfileMapper;
import com.example.datahubwebsite.Models.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserDao {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(User user){ // test 완료

        String sql = "Insert Into `Member.user` (user_name, login_type, token) values (?,?,?)";

        jdbcTemplate.update(sql, user.getUser_name(), user.getLogin_type(), user.getToken());


    }

    public User readbyUserNo(int userNo){// test 완료

        String sql = "select * from `Member.user` WHERE user_no = ?";

        User user;
        try{
            user = jdbcTemplate.queryForObject(sql, new UserMapper(), userNo);
        } catch (EmptyResultDataAccessException e) {
            return null; // 결과가 없다면
        }

        return user;
    }
    public User readbyToken(String Token){// test 완료

        String sql = "select * from `Member.user` WHERE token = ?";

        User user;

        try{
            user = jdbcTemplate.queryForObject(sql, new UserMapper(), Token);
        } catch (EmptyResultDataAccessException e) {
            return null; // 결과가 없다면
        }

//        System.out.println("read by Token"+user.getUser_no());
        return user;
    }

    public void updatebyUserNo(User user){// test 미완료

        String sql = "Update `Member.user` SET user_name = ?, login_type = ?, token = ? where user_no = ?";


        jdbcTemplate.update(sql, user.getUser_name(), user.getLogin_type(), user.getToken(), user.getUser_no() );

    }

    public void deletebyUserNo(int userNo){ // test 미완료
        String sql = "delete from `Member.user` WHERE user_no = ?";
        jdbcTemplate.update(sql, userNo);

    }


}
