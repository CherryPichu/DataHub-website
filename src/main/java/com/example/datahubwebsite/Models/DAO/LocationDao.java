package com.example.datahubwebsite.Models.DAO;

import com.example.datahubwebsite.Models.DTO.Location;
import com.example.datahubwebsite.Models.DTO.Password;
import com.example.datahubwebsite.Models.Mapper.LocationMapper;
import com.example.datahubwebsite.Models.Mapper.PasswordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@Component
public class LocationDao {
//    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void create(Location location){ // test 완료

        String sql = "Insert Into `Data.location` (lat, lng, fieldname, user_no, detail, data) values (?,?,?, ?, ?, ?)";

        jdbcTemplate.update(sql,location.getLat() , location.getLng(), location.getFieldname(),
                location.getUser_no(), location.getDetail(), location.getData());


    }

    public List<Location> readbyUserNo(int userNo){// test 완료

        List<Location> ListLoc;

        String sql = "select * from `Data.location` WHERE user_no = " + userNo;

        ListLoc = jdbcTemplate.query(sql, new LocationMapper());

        return ListLoc;
    }


    public List<Location> readbylocationNo(int no){// test 완료

        String sql = "select * from `Data.location` WHERE location_id = ?";

        List<Location> locations = jdbcTemplate.query(sql, new LocationMapper(), no);

        return locations;
    }

    public void updatebylocationNo(Location location){// test 미완료

        String sql = "Update `Data.location` SET lat = ? , lng = ?, fieldname = ?, user_no = ? , detail = ?, data= ? where location_id = ?";
        jdbcTemplate.update(sql, location.getLat(), location.getLng(), location.getFieldname(),
                location.getUser_no(), location.getDetail(), location.getLocation_id() , location.getData());

    }

    public void deletebylocationNo(int locatioinNo){ // test 미완료
        String sql = "delete from `Data.location` WHERE location_id = ?";
        jdbcTemplate.update(sql, locatioinNo);
    }

}
