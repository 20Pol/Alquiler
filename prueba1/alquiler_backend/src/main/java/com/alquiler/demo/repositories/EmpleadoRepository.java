package com.alquiler.demo.repositories;

import com.alquiler.demo.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    // 🔹 Buscar por correo electrónico (único normalmente)
    Optional<Empleado> findByEmail(String email);

    // 🔹 Buscar por nombre o apellido (coincidencia parcial)
    List<Empleado> findByNombreContainingOrApellidoContaining(String nombre, String apellido);

    // 🔹 Buscar empleados por cargo exacto
    List<Empleado> findByCargo(String cargo);

    // 🔹 Buscar empleados de una sucursal específica
    List<Empleado> findBySucursal_IdSucursal(Integer idSucursal);

    // 🔹 Buscar por teléfono exacto
    Optional<Empleado> findByTelefono(String telefono);
}
