package com.alquiler.demo.repositories;

import com.alquiler.demo.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    // 🔹 Buscar sucursales por nombre (coincidencia parcial)
    List<Sucursal> findByNombreContainingIgnoreCase(String nombre);

    // 🔹 Buscar sucursales por ciudad
    List<Sucursal> findByCiudadIgnoreCase(String ciudad);

    // 🔹 Buscar sucursales por dirección (coincidencia parcial)
    List<Sucursal> findByDireccionContainingIgnoreCase(String direccion);

    // 🔹 Buscar sucursal por teléfono exacto
    Sucursal findByTelefono(String telefono);

    // 🔹 Buscar sucursales en una ciudad específica ordenadas por nombre
    List<Sucursal> findByCiudadOrderByNombreAsc(String ciudad);
}
