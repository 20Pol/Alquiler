package com.alquiler.demo.services;

import com.alquiler.demo.entities.Contrato;
import java.util.List;
import java.util.Optional;

public interface ContratoService {
    List<Contrato> findAll();
    Optional<Contrato> findById(Integer id);
    Contrato save(Contrato contrato);
    Contrato update(Integer id, Contrato contrato);
    void delete(Integer id);
}
