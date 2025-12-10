package com.alquiler.demo.services;

import java.util.List;

import com.alquiler.demo.entities.Vehiculo;

public interface VehiculoService {

    List<Vehiculo> findAll();

    Vehiculo obtenerPorId(Integer id);

    List<Vehiculo> buscarPorMarca(String marca);

    List<Vehiculo> buscarPorAnio(Integer anio);

    List<Vehiculo> buscarPorTipo(String tipoVehiculo);

    Vehiculo save(Vehiculo vehiculo);

    Vehiculo update(Integer id, Vehiculo vehiculo);

    void delete(Integer id);
}
