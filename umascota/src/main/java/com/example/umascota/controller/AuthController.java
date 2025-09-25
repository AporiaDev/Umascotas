package com.example.umascota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.umascota.model.Usuario;
import com.example.umascota.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // Registro de usuario
    @PostMapping("/registro")
    public String register(@RequestBody Usuario user) {
        usuarioService.registrarUsuario(user);
        return "Usuario registrado con éxito";
    }

    // Login seguro
    @PostMapping("/login")
    public String login(@RequestBody Usuario user) {
        boolean loginValido = usuarioService.validarLogin(user.getCorreoElectronico(), user.getContrasena());

        if (loginValido) {
            return "Login exitoso. Bienvenido, " + user.getCorreoElectronico();
        } else {
            return "Usuario o contraseña incorrectos";
        }
    }
}
