package com.alquiler.demo.services;

import com.alquiler.demo.entities.Empleado;
import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> findAll();
    Optional<Empleado> findById(Integer id);
    Empleado save(Empleado empleado);
    Empleado update(Integer id, Empleado empleado);
    void delete(Integer id);
}
