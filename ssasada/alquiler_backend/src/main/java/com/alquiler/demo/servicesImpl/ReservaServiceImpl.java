package com.alquiler.demo.servicesImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Reserva;
import com.alquiler.demo.repositories.ReservaRepository;
import com.alquiler.demo.services.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

    
    private final ReservaRepository reservaRepository;
    
    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }


    @Override
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
    
    @Override
	public List<Reserva> findAll() {
		return reservaRepository.findAll();
	}


    @Override
    public Reserva update(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public void deleteById(Integer id) {
        reservaRepository.deleteById(id);
    }
    
    @Override
    public Reserva findById(Integer id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    @Override
    public Reserva actualizarEstado(Integer id, String estado) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(id);
        if (reservaOpt.isPresent()) {
            Reserva reserva = reservaOpt.get();
            reserva.setEstado(estado); // actualizamos solo el estado
            return reservaRepository.save(reserva);
        } else {
            throw new RuntimeException("Reserva no encontrada con ID: " + id);
        }
    }

}
