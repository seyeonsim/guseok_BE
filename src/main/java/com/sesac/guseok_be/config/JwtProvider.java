package com.sesac.guseok_be.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Key 타입으로 설정
    private final long EXPIRATION_TIME = 86400000; // 1일

    public String createToken(String email) {
        try {
            String token = Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                    .compact();
            System.out.println("JWT 생성 성공: " + token);
            return token;
        } catch (Exception e) {
            System.err.println("JWT 생성 실패: " + e.getMessage());
            throw e;
        }
    }
}