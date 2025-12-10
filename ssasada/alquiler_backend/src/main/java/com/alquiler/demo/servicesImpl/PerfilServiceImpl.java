package com.alquiler.demo.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Cliente;
import com.alquiler.demo.entities.Pago;
import com.alquiler.demo.entities.Reserva;
import com.alquiler.demo.repositories.ClienteRepository;
import com.alquiler.demo.repositories.PagoRepository;
import com.alquiler.demo.repositories.ReservaRepository;
import com.alquiler.demo.services.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService {

    private final ClienteRepository clienteRepository;
    private final ReservaRepository reservaRepository;
    private final PagoRepository pagoRepository;

    public PerfilServiceImpl(ClienteRepository clienteRepository,
            ReservaRepository reservaRepository,
            PagoRepository pagoRepository) {
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
        this.pagoRepository = pagoRepository;
    }

    @Override
    public Cliente obtenerClientePorId(Integer id) {
        return clienteRepository.findByUsuario_IdUsuario(id).orElse(null);
    }

    @Override
    public List<Reserva> obtenerReservasPorCliente(Integer idUsuario) {
        Cliente cliente = clienteRepository.findByUsuario_IdUsuario(idUsuario).orElse(null);
        if (cliente == null)
            return List.of();
        return reservaRepository.findByClienteIdCliente(cliente.getIdCliente());
    }

    @Override
    public List<Pago> obtenerPagosPorCliente(Integer idUsuario) {
        Cliente cliente = clienteRepository.findByUsuario_IdUsuario(idUsuario).orElse(null);
        if (cliente == null)
            return List.of();
        return pagoRepository.findByClienteIdCliente(cliente.getIdCliente());
    }

}
