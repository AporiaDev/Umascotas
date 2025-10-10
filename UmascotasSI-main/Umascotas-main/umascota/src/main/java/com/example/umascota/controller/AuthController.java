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


    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario user) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(user);
            return ResponseEntity.ok("Usuario registrado exitosamente: " + nuevoUsuario.getCorreoElectronico());
        } catch (IllegalArgumentException e) {
            // mensaje  desde el service
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // cualquier otro error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        try {
            boolean loginValido = usuarioService.validarLogin(user.getCorreoElectronico(), user.getContrasena());
            if (loginValido) {
                // Generar token JWT
                String token = JwtUtil.generateToken(user.getCorreoElectronico());
                
                // Obtener información del usuario
                Usuario usuarioCompleto = usuarioService.findByCorreoElectronico(user.getCorreoElectronico());
                
                // Crear respuesta con token y datos del usuario
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Login exitoso. Bienvenido, " + user.getCorreoElectronico());
                response.put("token", token);
                response.put("usuario", Map.of(
                    "id", usuarioCompleto.getIdUsuario(),
                    "nombre", usuarioCompleto.getNombreCompleto(),
                    "correo", usuarioCompleto.getCorreoElectronico(),
                    "tipoUsuario", usuarioCompleto.getTipoUsuario().toString()
                ));
                
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
            }
        } catch (Exception e) {
            e.printStackTrace(); // log en consola
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno en el login: " + e.getMessage());
        }
    }
}
