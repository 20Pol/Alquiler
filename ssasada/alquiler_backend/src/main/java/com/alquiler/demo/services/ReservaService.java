package com.alquiler.demo.services;

import java.util.List;

import com.alquiler.demo.entities.Reserva;

public interface ReservaService {
   
	List<Reserva> findAll();
    Reserva save(Reserva reserva);
    
    Reserva update(Reserva reserva);

    void deleteById(Integer id);
    
    Reserva findById(Integer id); 
    
    Reserva actualizarEstado(Integer id, String estado);

}
