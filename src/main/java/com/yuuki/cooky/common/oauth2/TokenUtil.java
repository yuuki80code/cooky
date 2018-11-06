package com.yuuki.cooky.common.oauth2;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class TokenUtil {

    private static long EXPIRE_TIME;
    @Value("${cooky.tokenExpire}")
    public void setExpireTime(Long expireTime){
        TokenUtil.EXPIRE_TIME = expireTime;
    }
    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, Long userid, String secret) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("userid", userid)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return true;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static Long getUsernId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userid").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,xmin后过期
     * @param userid 用户id
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(Long userid, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME*1000);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("userid", userid)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void main(String args[]) {
        Long usernId = getUsernId("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MzkzMjM2NjksInVzZXJpZCI6M30.Bx67sMQw3Ty37PS-6osqRQfUS0493DI1Y1aZBA6DCbs");
        System.out.printf(usernId+"");
    }


}
