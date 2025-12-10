package com.alquiler.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.demo.entities.Cliente;
import com.alquiler.demo.entities.Pago;
import com.alquiler.demo.entities.Reserva;
import com.alquiler.demo.services.PerfilService;

@RestController
@RequestMapping("/api/perfil")
@CrossOrigin(origins = "http://localhost:4200") 
public class PerfilController {
	
	private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/{id}")
    public Cliente getCliente(@PathVariable Integer id) {
        return perfilService.obtenerClientePorId(id);
    }

    @GetMapping("/reservas/{id}")
    public List<Reserva> getReservas(@PathVariable Integer id) {
        return perfilService.obtenerReservasPorCliente(id);
    }

    @GetMapping("/pagos/{id}")
    public List<Pago> getPagos(@PathVariable Integer id) {
        return perfilService.obtenerPagosPorCliente(id);
    }

}
