package com.alquiler.demo.services;

import com.alquiler.demo.entities.Reserva;

public interface ReservaService {

    Reserva save(Reserva reserva);

    Reserva findById(Integer id);

    void deleteById(Integer id);

    boolean existsById(Integer id);

}
