package com.example.datahubwebsite.Models.DAO;

import com.example.datahubwebsite.Models.DTO.Location;
import com.example.datahubwebsite.Models.DTO.Password;
import com.example.datahubwebsite.Models.Mapper.LocationMapper;
import com.example.datahubwebsite.Models.Mapper.PasswordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

        String sql = "Insert Into `Data.location` (lat, lng, fieldname, user_no, detail) values (?,?,?, ?, ?)";

        jdbcTemplate.update(sql,location.getLat() , location.getLng(), location.getFieldname(),
                location.getUser_no(), location.getDetail());


    }

    public List<Location> readbyUserNo(int userNo){// test 완료

        List<Location> ListLoc;

        String sql = "select * from `Data.location` WHERE user_no = " + userNo;

        ListLoc = jdbcTemplate.query(sql, new LocationMapper());

        return ListLoc;
    }

    public Location readbyfieldName(String fieldName, int user_no){// test 완료

        Location location;

        String sql = "select * from `Data.location` WHERE fieldname = ? AND user_no = ?" ;
//        System.out.println(user_no+ "  :  "+ fieldName);
        try{
            location = jdbcTemplate.queryForObject(sql, new LocationMapper(), fieldName, user_no);
        } catch (EmptyResultDataAccessException e) {
            return null; // 결과가 없다면
        }
        if(location.getLocation_id() == 0){
            return null;
        }



        return location;
    }



    public List<Location> readbylocationNo(int no){// test 완료

        String sql = "select * from `Data.location` WHERE location_id = ?";

        List<Location> locations = jdbcTemplate.query(sql, new LocationMapper(), no);

        return locations;
    }

    public void updatebylocationNo(Location location){// test 미완료

        String sql = "Update `Data.location` SET lat = ? , lng = ?, fieldname = ?, user_no = ? , detail = ? where location_id = ?";
        jdbcTemplate.update(sql, location.getLat(), location.getLng(), location.getFieldname(),
                location.getUser_no(), location.getDetail(), location.getLocation_id() );

    }


    public void deletebylocationNo(int locatioinId){ // test 미완료
        String sql = "delete from `Data.location` WHERE location_id = ?";
        jdbcTemplate.update(sql, locatioinId);
    }

}
