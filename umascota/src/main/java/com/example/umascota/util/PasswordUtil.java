package com.example.umascota.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String encriptar(String password) {
        return encoder.encode(password);
    }
    public static boolean verificar(String password, String hash) {
        return encoder.matches(password, hash);
    }
}
