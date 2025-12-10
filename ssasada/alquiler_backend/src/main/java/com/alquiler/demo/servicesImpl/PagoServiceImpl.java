package com.alquiler.demo.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Pago;
import com.alquiler.demo.repositories.PagoRepository;
import com.alquiler.demo.services.PagoService;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> findById(Integer id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago update(Integer id, Pago pago) {
        if (pagoRepository.existsById(id)) {
            pago.setIdPago(id);
            return pagoRepository.save(pago);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        pagoRepository.deleteById(id);
    }
}
