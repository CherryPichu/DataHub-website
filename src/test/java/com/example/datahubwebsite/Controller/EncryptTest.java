package com.example.datahubwebsite.Controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@WebAppConfiguration
@SpringBootTest
public class EncryptTest {

    @DisplayName("Encrypt 테스트")
    @Test
    void TESTEncrypt() {
//        String salt = Encrypt.getSalt();
        String passwd = "namjung";
        String testPw = BCrypt.hashpw( passwd, BCrypt.gensalt() );
        String targetPw = "namjung";
        Boolean result = BCrypt.checkpw(targetPw, testPw); // 비교

//        Bcrypt.
//        assertEquals( result,  );
        assertFalse( !result );
    }


}
