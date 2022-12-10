package com.example.datahubwebsite.Controllers;

import com.example.datahubwebsite.Models.DAO.LocationDao;
import com.example.datahubwebsite.Models.DAO.PasswordDao;
import com.example.datahubwebsite.Models.DAO.ProfileDao;
import com.example.datahubwebsite.Models.DAO.UserDao;
import com.example.datahubwebsite.Models.DTO.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class DataController {

    @Autowired
    private UserDao userdb;

    @Autowired
    private PasswordDao passworddb;

    @Autowired
    private ProfileDao profiledb;

    @Autowired
    private LocationDao locationdb;

    @PostMapping (value="/api/postLocationData")
    public String postLocationData(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(value = "lat") double lat, @RequestParam(value ="lng") double lng,
                                   @RequestParam(value = "fieldname") String fieldname, @RequestParam(value ="user_no") int user_no,
                                   @RequestParam(value ="detail") String detail, @RequestParam(value ="token") String token){

        HttpSession session = request.getSession();
        int userNo;
        if(session.getAttribute("user_no") != null) {
            userNo = (int) session.getAttribute("user_no");
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "fail";
        }

        if(userdb.readbyToken(token) != null && locationdb.readbyfieldName(fieldname) != null ){ // 잘못된 토큰 입력시 에러. 500
            Location location = new Location(lat, lng, fieldname, userNo, detail );
            locationdb.create(location);
            return "success";
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "faill";
        }
//        Location location = new Location(lat, lng, fieldname, userNo, detail );
//        locationdb.create(location);
//        return "success";

    }

    @GetMapping(value="/api/getLocationData")
    public List<Location> getLocationData(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        int userNo;
        List<Location> locations;
        if(session.getAttribute("user_no") != null){
            System.out.println(session.getAttribute("user_no") );
            userNo = (int) session.getAttribute("user_no");
            locations = locationdb.readbyUserNo( userNo );
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            locations = locationdb.readbyUserNo( -1 ); // 에러 발생
        }

        System.out.println(locations.toString() );
        return locations;

    }



}
