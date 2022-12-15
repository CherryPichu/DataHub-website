package com.example.datahubwebsite.Models.Mapper;

import com.example.datahubwebsite.Models.DTO.Location;
import com.example.datahubwebsite.Models.DTO.Password;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
//        ArrayList<Location> arrLocation = new ArrayList<Location>();
        Location location = new Location();
        location.setLat(rs.getDouble("lat"));
        location.setLng(rs.getDouble("lng"));
        location.setFieldname(rs.getString("fieldname"));
        location.setDetail(rs.getString("detail"));


        return location;
    }

}
