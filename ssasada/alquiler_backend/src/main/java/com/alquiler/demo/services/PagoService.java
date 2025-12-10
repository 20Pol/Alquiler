package com.alquiler.demo.services;

import com.alquiler.demo.entities.Pago;
import java.util.List;
import java.util.Optional;

public interface PagoService {
    List<Pago> findAll();
    Optional<Pago> findById(Integer id);
    Pago save(Pago pago);
    Pago update(Integer id, Pago pago);
    void delete(Integer id);
}
