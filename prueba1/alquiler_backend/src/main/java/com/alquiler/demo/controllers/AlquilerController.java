package com.alquiler.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlquilerController {

    @GetMapping("/alquiler_devolucion")
    public String devolverAlquiler() {
        return "admin/alquiler_devolucion";
    }

    @GetMapping("/alquiler_form")
    public String nuevoAlquiler() {
        return "admin/alquiler_form";
    }

    @GetMapping("/alquiler_listar")
    public String listarAlquiler() {
        return "admin/alquiler_listar";
    }
}