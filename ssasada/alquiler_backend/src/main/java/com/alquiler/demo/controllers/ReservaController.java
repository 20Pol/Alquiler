package com.alquiler.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.demo.entities.Reserva;
import com.alquiler.demo.services.ReservaService;

@RestController
@RequestMapping("/api/reserva")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {
	
	private final ReservaService reservaService;
	
	
	public ReservaController(ReservaService reservaService) {
		this.reservaService = reservaService;
	}


	@PostMapping
		public Reserva save(@RequestBody Reserva reserva) {
			return reservaService.save(reserva);
		}
	
	   @GetMapping
	    public List<Reserva> findAll() {
	        return reservaService.findAll();
	    }
	   
	   @DeleteMapping("/{id}")
	    public void eliminarReserva(@PathVariable Integer id) {
	        reservaService.deleteById(id);
	    }
	   
	   @PutMapping("/{id}/estado")
	    public Reserva actualizarEstado(@PathVariable Integer id, @RequestParam String estado) {
	        return reservaService.actualizarEstado(id, estado);
	    }

}
