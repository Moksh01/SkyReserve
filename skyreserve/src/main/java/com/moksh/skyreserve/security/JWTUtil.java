package com.moksh.skyreserve.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    private static final String secret_key="5367566859703373367639792F423F452848284D6251655468576D5A71347437";
    private  static final Key key= Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));



    public String generateToken(String username,long expiryTime){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis()+expiryTime*60*1000))
                .signWith(SignatureAlgorithm.HS256,key).
                compact();
    }
    public String validateAndExtractUsername(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            throw null;
        }
    }
}
