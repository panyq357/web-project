package cn.ac.panlab.backend.util;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    @Autowired
    private SecretKey jwtKey;

    @Value("${app.jwt.expiration}")
    private long expiration;

    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
            .claims(claims)
            .signWith(jwtKey)
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
            .verifyWith(jwtKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
