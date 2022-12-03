package com.example.datahubwebsite.Controllers;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

// 사용 : https://cceeun.tistory.com/241
public class Encrypt{
    public static String getSalt(){
        SecureRandom r = new SecureRandom();
        byte[] salt = new byte[20];

        // 2. 난수 생성
        r.nextBytes(salt);

        // 3. byte To String ( 10진수의 문자열로 변경)
        StringBuffer sb = new StringBuffer();
        for(byte b : salt){
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    /**
     * Function 암호화 (pwd + salt)
     * @param pwd 암호
     * @param salt getSalt();
     * @return SHA-256 알고리즘으로 암호화된 pwd
     */
    public static String getEncrypt(String pwd, String salt ){
        String result = "";
        try{
            // 1. SHA256 알고리즘 객체 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // 2. pwd와 salt 합친 문자열에 SHA 256 적용
            System.out.println("pwd + salt 적용 전 : " + pwd + salt);
            md.update((pwd+salt).getBytes());
            byte[] pwdsalt = md.digest();

            // 3. byte To String ( 10진수의 문자열로 변경)
            StringBuffer sb = new StringBuffer();
            for(byte b : pwdsalt){
                sb.append(String.format("%02x", b));
            }

            result = sb.toString();
            System.out.println("pwd + salt 적용 후  : " + result);

        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


}