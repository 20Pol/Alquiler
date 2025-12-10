package com.alquiler.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/dashboard")
    public String mostrarDashboard() {      
        return "admin/dashboard";  
    }
	
	@GetMapping("/cliente_detalle")
    public String mostrarClienteDetalle() {
        return "admin/cliente_detalle";
    }
	
	@GetMapping("/cliente_form")
    public String mostrarClienteForm() {
        return "admin/cliente_form";
    }
	
	@GetMapping("/cliente_listar")
    public String mostrarListaClientes() {
        return "admin/cliente_listar";
    }
	
	@GetMapping("/roles")
    public String mostrarRoles() {
        return "admin/roles";
    }

}
