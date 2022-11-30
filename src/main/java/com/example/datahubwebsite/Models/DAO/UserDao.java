package com.example.datahubwebsite.Models.DAO;

import com.example.datahubwebsite.Models.DTO.User;
import com.example.datahubwebsite.Models.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    public void add(User user){
        Connection db;
        try {
            db = DataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "Insert Into User (user_name, login_type, token) values (?,?,?)";
//        db.prepareStatement();
//
//        db.
//
//        db.close();
    }
}
