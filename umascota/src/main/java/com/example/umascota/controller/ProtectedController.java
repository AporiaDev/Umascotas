package com.example.umascota.controller;

import com.example.umascota.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protected")
public class ProtectedController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/hello")
    public ResponseEntity<?> hello(@RequestHeader(name = "Authorization", required = false) String authorization) {
        try {
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falta header Authorization Bearer");
            }

            String token = authorization.substring("Bearer ".length());
            Claims claims = jwtUtil.validateAndGetClaims(token);

            String subject = claims.getSubject();
            String role = (String) claims.get("role");

            return ResponseEntity.ok("Hola " + subject + ", tu rol es " + role + ". Token válido.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido: " + e.getMessage());
        }
    }
}


