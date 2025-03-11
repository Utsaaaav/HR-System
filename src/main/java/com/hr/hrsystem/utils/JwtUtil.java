package com.hr.hrsystem.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private String SECRET_KEY = "45PfHxuCYQcfCR3zkRPZjizLjaHWMD9R5vw+AoYjFHAWkcKDxo/SlYIi5tvB2RV4";

    public String generateToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object>claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("typ", "JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey())
                .build().parseSignedClaims(token).
                getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Date expirationDate(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return expirationDate(token).before(new Date());
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }



}
