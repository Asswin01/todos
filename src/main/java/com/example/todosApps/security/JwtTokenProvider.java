package com.example.todosApps.security;

import org.springframework.beans.factory.annotation.Value;

public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-millisecond}")
    private String jwtExpirationDate;


    // Generate JWT token
    

}
