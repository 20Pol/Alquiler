package com.alquiler.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.demo.entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

	List<Pago> findByClienteIdCliente(Integer idCliente);
}
