package com.yuuki.cooky.token;

import com.yuuki.cooky.common.oauth2.TokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenTest {


    @Test
    public void genTken(){
        System.out.println(TokenUtil.sign(1l,"123456"));
    }

    @Test
    public void expireToken() throws UnsupportedEncodingException {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDE0MjY0NDksInVzZXJpZCI6MX0.QkmzzrAZq1isKe2x0fgZYyBF96xc1j0ADM0_7Jto3Uk";

        System.out.println(TokenUtil.verify(token,1l,"12356"));
    }

}
