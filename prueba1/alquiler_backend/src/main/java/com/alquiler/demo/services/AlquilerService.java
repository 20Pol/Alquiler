package com.alquiler.demo.services;

import com.alquiler.demo.entities.Alquiler;
import java.util.List;
import java.util.Optional;

public interface AlquilerService {
    List<Alquiler> findAll();
    Optional<Alquiler> findById(Integer id);
    Alquiler save(Alquiler alquiler);
    Alquiler update(Integer id, Alquiler alquiler);
    void delete(Integer id);
}
