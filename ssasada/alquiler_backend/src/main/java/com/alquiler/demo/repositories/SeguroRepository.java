package com.alquiler.demo.repositories;

import com.alquiler.demo.entities.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Integer> {

    // 🔹 Buscar seguros por compañía aseguradora
    List<Seguro> findByCompaniaContainingIgnoreCase(String compania);

    // 🔹 Buscar seguros por tipo de cobertura (ej: "Todo riesgo", "Contra terceros")
    List<Seguro> findByTipoCobertura(String tipoCobertura);

    // 🔹 Buscar seguros que vencen antes de una fecha específica
    List<Seguro> findByFechaFinBefore(LocalDate fechaLimite);

    // 🔹 Buscar seguros que están vigentes en una fecha determinada
    List<Seguro> findByFechaInicioBeforeAndFechaFinAfter(LocalDate inicio, LocalDate fin);

    // 🔹 Buscar seguros de un vehículo específico
    List<Seguro> findByVehiculo_IdVehiculo(Integer idVehiculo);

    // 🔹 Buscar seguros con costo mayor a un monto determinado
    List<Seguro> findByCostoGreaterThan(BigDecimal montoMinimo);

    // 🔹 Buscar seguros dentro de un rango de costo
    List<Seguro> findByCostoBetween(BigDecimal montoMin, BigDecimal montoMax);
}
