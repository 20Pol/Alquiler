package com.alquiler.demo.servicesImpl;

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
    public Reserva findById(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return reservaRepository.existsById(id);
    }

}
