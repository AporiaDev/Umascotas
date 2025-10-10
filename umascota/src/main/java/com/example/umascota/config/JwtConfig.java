package com.example.umascota.config;

import com.example.umascota.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtUtil jwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expirationMillis}") long expirationMillis,
            @Value("${jwt.issuer}") String issuer
    ) {
        return new JwtUtil(secret, expirationMillis, issuer);
    }
}


