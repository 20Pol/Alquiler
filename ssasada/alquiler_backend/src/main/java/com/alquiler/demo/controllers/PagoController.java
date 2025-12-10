package com.alquiler.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagoController {

    @GetMapping("/pagos")
    public String listarPagos() {
        return "admin/pagos";
    }

    @GetMapping("/admin/factura")
    public String generarFactura() {
        return "admin/factura";
    }
}
