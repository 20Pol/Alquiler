package com.alquiler.demo.services;

import com.alquiler.demo.entities.Seguro;
import java.util.List;
import java.util.Optional;

public interface SeguroService {
    List<Seguro> findAll();
    Optional<Seguro> findById(Integer id);
    Seguro save(Seguro seguro);
    Seguro update(Integer id, Seguro seguro);
    void delete(Integer id);
}
