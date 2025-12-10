package com.alquiler.demo.services;

import com.alquiler.demo.entities.Mantenimiento;
import java.util.List;
import java.util.Optional;

public interface MantenimientoService {
    List<Mantenimiento> findAll();
    Optional<Mantenimiento> findById(Integer id);
    Mantenimiento save(Mantenimiento mantenimiento);
    Mantenimiento update(Integer id, Mantenimiento mantenimiento);
    void delete(Integer id);
}
