package com.example.umascota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.umascota.model.Usuario;
import com.example.umascota.service.UsuarioService;
import com.example.umascota.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    // Registro de usuario
    @PostMapping("/registro")
    public String register(@RequestBody Usuario user) {
        usuarioService.registrarUsuario(user);
        return "Usuario registrado con éxito";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        try {
            boolean loginValido = usuarioService.validarLogin(user.getCorreoElectronico(), user.getContrasena());

            if (loginValido) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("role", "USER");
                String token = jwtUtil.generateToken(user.getCorreoElectronico(), claims);

                Map<String, Object> body = new HashMap<>();
                body.put("token", token);
                body.put("tipo", "Bearer");
                return ResponseEntity.ok(body);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
            }
        } catch (Exception e) {
            e.printStackTrace(); // log en consola
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno en el login: " + e.getMessage());
        }
    }
}
