package com.example.RailReservation.Utill;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET = "Durai Srivai Mech FullStack Developer";
    private long EXPIRATION = 1000 * 60 * 30;
    private final Key secretkey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email){

        return Jwts.builder().setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretkey, SignatureAlgorithm.HS256).compact();
    }
    public String extractEmail(String token){
        return Jwts.parserBuilder().setSigningKey(secretkey)
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String token){
        try{
            extractEmail(token);
            return true;
        }
        catch (JwtException exception){
            return false;
        }
    }


}
