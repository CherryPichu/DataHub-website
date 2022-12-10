package com.example.datahubwebsite.Models.Mapper;

import com.example.datahubwebsite.Models.DTO.DataDto;
import com.example.datahubwebsite.Models.DTO.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataMapper implements RowMapper<DataDto> {

    @Override
    public DataDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//        ArrayList<Location> arrLocation = new ArrayList<Location>();
        DataDto data = new DataDto();
        data.setData_id(rs.getInt("data_id"));
        data.setData(rs.getString("data"));
        data.setLocation_id(rs.getInt("location_id"));
        data.setCreateAt(rs.getString("CreateAt"));


        return data;
    }

}
