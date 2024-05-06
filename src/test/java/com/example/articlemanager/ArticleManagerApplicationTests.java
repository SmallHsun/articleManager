package com.example.articlemanager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ArticleManagerApplicationTests {

    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","何孟勳");
        String token = JWT.create()
                .withClaim("user", claims)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
                .sign(Algorithm.HMAC256("i12385251"));

        System.out.println(token);
    }

    @Test
    public void parseToken() {
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
                +".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuS9leWtn-WLsyJ9LCJpYXQiOjE3MTQ0ODU2ODksImV4cCI6MTcxNDQ5NjQ4OX0"
                +".XLmQVp0px0pdNZVW2sH89s4QhUBKa-QJCIr5yeimVJo";

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("i12385251"))
                .build()
                .verify(token);
        System.out.println(decodedJWT.getClaims().get("user"));
    }
}

