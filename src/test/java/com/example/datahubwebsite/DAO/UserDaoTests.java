package com.example.datahubwebsite.DAO;

import com.example.datahubwebsite.Controllers.TokenController;
import com.example.datahubwebsite.Models.DTO.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Member;
import java.util.Optional;

@SpringBootTest
public class UserDaoTests {
    
    @DisplayName("DAO TEST")
    @Test
    void TESTUserDAO() {

        // given
//        User user = new User("namjung",0,"");
        String test = TokenController.createToken("test");
        System.out.println( test );
        TokenController.parseJwtToken(test);


    }

}
