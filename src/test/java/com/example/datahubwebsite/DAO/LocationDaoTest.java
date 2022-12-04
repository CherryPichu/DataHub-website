package com.example.datahubwebsite.DAO;

import com.example.datahubwebsite.Models.DAO.LocationDao;
import com.example.datahubwebsite.Models.DTO.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LocationDaoTest {

    @DisplayName("DAO TEST")
    @Test
    void TESTUserDAO() {

        LocationDao locationdb = new LocationDao();

        System.out.println("location db 생성");
    }

}
