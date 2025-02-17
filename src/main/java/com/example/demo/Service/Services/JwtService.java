package com.example.demo.Service.Services;

import com.example.demo.Domain.Models.User;
import com.example.demo.Service.Interfaces.IJwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService implements IJwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(User user) {
        return Jwts.builder()
            .setSubject(user.getUsername())
            .claim("roles", new String[]{"USER", "ADMIN"})
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact();
    }
}
