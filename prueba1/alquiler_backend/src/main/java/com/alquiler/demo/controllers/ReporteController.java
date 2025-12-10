package com.alquiler.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReporteController {

    @GetMapping("/reportes")
    public String listarReportes() {
        return "admin/reportes";
    }

    @GetMapping("/reportes/vehiculos")
    public String reporteVehiculos() {
        return "reportes/vehiculos";
    }

}