package com.alquiler.demo.services;

import java.util.List;

import com.alquiler.demo.entities.Cliente;
import com.alquiler.demo.entities.Pago;
import com.alquiler.demo.entities.Reserva;

public interface PerfilService {
	
	Cliente obtenerClientePorId(Integer id);

    List<Reserva> obtenerReservasPorCliente(Integer idCliente);

    List<Pago> obtenerPagosPorCliente(Integer idCliente);

}
