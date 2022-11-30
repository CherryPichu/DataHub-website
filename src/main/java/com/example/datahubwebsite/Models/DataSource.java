package com.example.datahubwebsite.Models;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

//https://yjh5369.tistory.com/entry/HikariCP-%EC%86%8C%EA%B0%9C
public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl("");
        config.setUsername("root2");
        config.setPassword("n7gIpAzo");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("preStmtCacheSize", "250");
        config.addDataSourceProperty("preStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
}
