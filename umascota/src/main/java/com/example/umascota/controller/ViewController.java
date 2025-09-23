package com.example.umascota.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // Página principal para elegir login o registro
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("mensaje", "Bienvenido a U-Mascota");
        return "home"; // templates/home.html
    }

    // Vista de login
    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html
    }

    // Vista de registro
    @GetMapping("/register")
    public String register() {
        return "register"; // templates/register.html
    }
}
