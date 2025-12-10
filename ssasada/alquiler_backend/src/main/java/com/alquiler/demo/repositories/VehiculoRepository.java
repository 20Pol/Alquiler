package com.alquiler.demo.repositories;

import com.alquiler.demo.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    
    // 🔹 Buscar por marca 
    List<Vehiculo> findByMarcaIgnoreCase(String marca);

    // 🔹 Buscar vehículos por año
    List<Vehiculo> findByAnio(Integer anio);
    
    List<Vehiculo> findByTipoVehiculo(String tipoVehiculo);
    
    Optional<Vehiculo> findById(Integer id);
    
    


   
}
