package com.alquiler.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.demo.entities.Cliente;
import com.alquiler.demo.services.ClienteService;


@RestController
@RequestMapping(path = "/api/cliente")
@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class ClienteController {
	
	public final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}
    
    
    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable Integer id) {
        return clienteService.obtenerPorId(id);
    }

	

   
  
}