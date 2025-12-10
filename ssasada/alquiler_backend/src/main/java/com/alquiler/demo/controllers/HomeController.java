package com.alquiler.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
    public String mostrarInicio(Model model) {
        // Puedes enviar información dinámica al HTML
        model.addAttribute("titulo", "Bienvenido al Sistema de Alquiler de Vehículos");
        model.addAttribute("mensaje", "Gestiona tu alquiler de forma rápida y segura.");
        
        return "cliente/inicio";  // Carga la vista cliente/inicio.html
    }
}