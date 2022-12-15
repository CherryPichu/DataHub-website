package com.example.datahubwebsite.Controllers;

import com.example.datahubwebsite.Models.DAO.*;
import com.example.datahubwebsite.Models.DTO.DataDto;
import com.example.datahubwebsite.Models.DTO.Location;
import com.example.datahubwebsite.Models.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/data")
public class DataController {

    @Autowired
    private UserDao userdb;

    @Autowired
    private PasswordDao passworddb;

    @Autowired
    private ProfileDao profiledb;

    @Autowired
    private LocationDao locationdb;

    @Autowired
    private DataDao datadb;

    @PostMapping (value="/api/postLocationData")
    public String postLocationData(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(value = "lat") double lat, @RequestParam(value ="lng") double lng,
                                   @RequestParam(value = "fieldname") String fieldname, @RequestParam(value ="detail")
                                       String detail, @RequestParam(value ="token") String token){


        HttpSession session = request.getSession();
        int userNo;

        if(session.getAttribute("user_no") != null) {
            userNo = (int) session.getAttribute("user_no");
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return "null";
        }

        if( locationdb.readbyfieldName(fieldname, userNo) != null ){

        }

        if(userdb.readbyToken(token) != null ){ // 잘못된 토큰 입력시 에러. 500
            Location location = new Location(lat, lng, fieldname, userNo, detail );
            locationdb.create(location);
            return "success";
        }else{
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 - 내용 없음
            return "null";
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
//            System.out.println(session.getAttribute("user_no") );
            userNo = (int) session.getAttribute("user_no");
            locations = locationdb.readbyUserNo( userNo );
        }else{
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 - 내용 없음
            locations = locationdb.readbyUserNo( -1 ); // 에러 발생
        }

//        System.out.println(locations.toString() );
        return locations;
    }


    /**
     * Data.data 테이블에 데이터를 저장.
     */
    @GetMapping(value="/api/PostData")
    public String postData(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "data") String data,  @RequestParam(value = "token") String token,
                           @RequestParam(value= "fieldname") String fieldname){
        HttpSession session = request.getSession();

        User user = userdb.readbyToken(token);

        if(user == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return "user null";
        }


        Location location = locationdb.readbyfieldName(fieldname, user.getUser_no());
        if(location == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return "location error";
        }

        if( !user.getToken().equals( token ) ){ // 토큰이 일치해야만 데이터를 넣을 수 있다.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return "token  불일치";
        }

        /**
         * token과 fieldname이 다 일치한지 검증되었을 때
         * Data.data에 데이터를 추가함.
         */
        DataDto datadto = new DataDto( data, location.getLocation_id() );
        datadb.create(datadto);



        return "sucess";
    }


    /**
     * Data.data 테이블에 데이터를 최대 100개를 json 형태로 반환함.
     */
    @GetMapping(value="/api/GetData")
    public List<DataDto> getData(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "token") String token,
                                 @RequestParam(value= "fieldname") String fieldname){
        HttpSession session = request.getSession();

        User user = userdb.readbyToken(token);

        if(user == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return null;
        }


        Location location = locationdb.readbyfieldName(fieldname, user.getUser_no());
        if(location == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return null;
        }

        if( !user.getToken().equals( token ) ){ // 토큰이 일치해야만 데이터를 넣을 수 있다.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return null;
        }

        /**
         * token과 fieldname이 다 일치한지 검증되었을 때
         * Data.data에 데이터를 추가함.
         */

        List<DataDto> datalist = datadb.readbylocationId(location.getLocation_id());
        if(datalist == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return null;
        }

        return datalist;
    }


    @GetMapping(value="/api/DeleteData")
    public String deleteData(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "token") String token,
                                 @RequestParam(value= "fieldname") String fieldname){
        HttpSession session = request.getSession();

        User user = userdb.readbyToken(token);

        if(user == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return null;
        }


        Location location = locationdb.readbyfieldName(fieldname, user.getUser_no());
        if(location == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return null;
        }

        if( !user.getToken().equals( token ) ){ // 토큰이 일치해야만 데이터를 넣을 수 있다.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            return null;
        }

        /**
         * token과 fieldname이 다 일치한지 검증되었을 때
         * Data.data에 데이터를 추가함.
         */

        datadb.deletebyDataId( location.getLocation_id() );
        locationdb.deletebylocationNo( location.getLocation_id() );


        return "success";
    }

}
