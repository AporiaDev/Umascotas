package com.example.umascota.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desactiva CSRF para evitar problemas con APIs REST
            .csrf(csrf -> csrf.disable())

            // Desactiva el formulario de login por defecto
            .formLogin(form -> form.disable())

            // Desactiva autenticación básica en el navegador
            .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}
