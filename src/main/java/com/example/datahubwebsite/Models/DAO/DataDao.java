package com.example.datahubwebsite.Models.DAO;


import com.example.datahubwebsite.Models.DTO.DataDto;
import com.example.datahubwebsite.Models.DTO.Location;
import com.example.datahubwebsite.Models.Mapper.DataMapper;
import com.example.datahubwebsite.Models.Mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class DataDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void create(DataDto data){ // test 완료

        String sql = "Insert Into `Data.data` (data, location_id) values (?, ?)";

        jdbcTemplate.update(sql,data.getData() , data.getLocation_id() );

    }

    public List<DataDto> readbylocationId(int locationId){// test 완료

        List<DataDto> dataList;

        String sql = "select * from `Data.data` WHERE location_id = " + locationId + " ORDER BY CreateAt DESC LIMIT 1000;";

        dataList = jdbcTemplate.query(sql, new DataMapper());

        return dataList;
    }


    public List<DataDto> readbyDataId(int dataId){// test 완료

        List<DataDto> dataList;

        String sql = "select * from `Data.data` WHERE data_id = " + dataId;

        dataList = jdbcTemplate.query(sql, new DataMapper());

        return dataList;
    }

    public void updatebylocationNo(DataDto data){// test 미완료

        String sql = "Update `Data.data` SET data = ? where location_id = ?";
        jdbcTemplate.update(sql,  data.getData(), data.getLocation_id());

    }

    public void deletebyDataId(int data_id){ // test 미완료
        String sql = "delete from `Data.data` WHERE location_id = ?";
        jdbcTemplate.update(sql, data_id);
    }
    public void deletebylocationNo(int location_no){ // test 미완료
        String sql = "delete from `Data.data` WHERE location_no = ?";
        jdbcTemplate.update(sql, location_no);
    }

}
