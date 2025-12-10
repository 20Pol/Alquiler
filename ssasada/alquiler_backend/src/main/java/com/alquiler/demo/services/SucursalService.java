package com.alquiler.demo.services;

import com.alquiler.demo.entities.Sucursal;
import java.util.List;
import java.util.Optional;

public interface SucursalService {
    List<Sucursal> findAll();
    Optional<Sucursal> findById(Integer id);
    Sucursal save(Sucursal sucursal);
    Sucursal update(Integer id, Sucursal sucursal);
    void delete(Integer id);
}
