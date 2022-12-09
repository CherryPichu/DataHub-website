package com.example.datahubwebsite.Models.DAO;

import com.example.datahubwebsite.Controllers.ForbiddenException;
import com.example.datahubwebsite.Models.DTO.Password;
import com.example.datahubwebsite.Models.DTO.Profile;
import com.example.datahubwebsite.Models.Mapper.PasswordMapper;
import com.example.datahubwebsite.Models.Mapper.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class PasswordDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Password password){ // test 완료

        String sql = "Insert Into `Auth.password` (user_no, password, nickname) values (?,?,?)";

        jdbcTemplate.update(sql, password.getUser_no(), password.getPassword(), password.getNickname());


    }

    public Password readbyUserNo(int userNo){// test 완료

        String sql = "select * from `Auth.password` WHERE user_no = ?";

        Password password = jdbcTemplate.queryForObject(sql, new PasswordMapper(), userNo);

        return password;
    }

    /**
     * 결과가 없을 경우 빈 Password객체 반환 또는 password == null
     * @param NickName
     * @return
     */
    public Password readbyNickName(String NickName){// test 완료

        String sql = "select * from `Auth.password` WHERE nickname = ?";
        Password password;
        try{
            password = jdbcTemplate.queryForObject(sql, new PasswordMapper(), NickName);
        } catch (EmptyResultDataAccessException e) {
//            throw new ForbiddenException();
            return null; // 결과가 없다면
        }




        return password;
    }


    public void updatebyUserNo(Password password){// test 미완료

        String sql = "Update `Auth.password` SET password = ? , nickname = ? where user_no = ?";
        jdbcTemplate.update(sql, password.getPassword(), password.getNickname(), password.getUser_no() );

    }

    public void deletebyUserNo(int userNo){ // test 미완료
        String sql = "delete from `Auth.password` WHERE user_no = ?";
        jdbcTemplate.update(sql, userNo);
    }
}
