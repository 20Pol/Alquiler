package com.alquiler.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/{id}")
	public ResponseEntity<Reserva> getById(@PathVariable Integer id) {
		Reserva reserva = reservaService.findById(id);
		if (reserva != null) {
			return ResponseEntity.ok(reserva);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public Reserva save(@RequestBody Reserva reserva) {
		return reservaService.save(reserva);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (reservaService.existsById(id)) {
			reservaService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
