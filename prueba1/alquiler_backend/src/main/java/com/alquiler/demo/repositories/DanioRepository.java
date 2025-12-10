package com.alquiler.demo.repositories;

import com.alquiler.demo.entities.Danio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DanioRepository extends JpaRepository<Danio, Integer> {

    // 🔹 Buscar daños registrados entre dos fechas
    List<Danio> findByFechaRegistroBetween(LocalDate inicio, LocalDate fin);

    // 🔹 Buscar daños registrados por un empleado específico
    List<Danio> findByEmpleado_IdEmpleado(Integer idEmpleado);

    // 🔹 Buscar daños asociados a un alquiler específico
    List<Danio> findByAlquiler_IdAlquiler(Integer idAlquiler);

    // 🔹 Buscar daños cuyo costo estimado sea mayor a un valor dado
    List<Danio> findByCostoEstimadoGreaterThan(BigDecimal costoMinimo);

    // 🔹 Buscar daños cuyo costo estimado esté entre dos valores
    List<Danio> findByCostoEstimadoBetween(BigDecimal min, BigDecimal max);
}

