package com.example.umascota.controller;

import com.example.umascota.model.Mascota;
import com.example.umascota.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/adoptante")
public class AdoptanteController {

    @Autowired
    private MascotaRepository mascotaRepository;

    // Vista principal para adoptantes - lista de mascotas disponibles
    @GetMapping("/mascotas-disponibles")
    public String mascotasDisponibles(Model model) {
        // Obtener solo mascotas disponibles
        List<Mascota> mascotasDisponibles = mascotaRepository.findByEstadoPublicacion(Mascota.EstadoPublicacion.disponible);
        model.addAttribute("mascotas", mascotasDisponibles);
        return "view/adoptante/mascotas-disponibles";
    }

    // Vista de perfil del adoptante
    @GetMapping("/perfil")
    public String perfilAdoptante() {
        return "view/adoptante/perfil";
    }

    // Vista de solicitudes de adopción
    @GetMapping("/mis-solicitudes")
    public String misSolicitudes() {
        return "view/adoptante/mis-solicitudes";
    }
}
