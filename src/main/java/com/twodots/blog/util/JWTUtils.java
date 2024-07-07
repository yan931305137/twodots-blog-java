package com.twodots.blog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * JWTUtils
 *
 * @ date 2024/7/4 1:14
 */
public class JWTUtils {

    private static final String SIGN = "WSSCG";

    //    生成token
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 1);

        //  创建builder
        JWTCreator.Builder builder = JWT.create();
        //  payload
        map.forEach(builder::withClaim);

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
    }

    //    验证token
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
