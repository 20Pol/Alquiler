package com.alquiler.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.demo.entities.Alquiler;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {

    // Buscar alquileres por rango de fechas reales de inicio
    List<Alquiler> findByFechaInicioRealBetween(LocalDate inicio, LocalDate fin);

    // Buscar alquileres que terminen antes de cierta fecha
    List<Alquiler> findByFechaFinRealBefore(LocalDate fecha);

    // Buscar alquileres que aún no tienen fecha de fin (por ejemplo, en curso)
    List<Alquiler> findByFechaFinRealIsNull();

    // Buscar por contrato (usando el id del contrato)
    List<Alquiler> findByContrato_IdContrato(Integer idContrato);
}
