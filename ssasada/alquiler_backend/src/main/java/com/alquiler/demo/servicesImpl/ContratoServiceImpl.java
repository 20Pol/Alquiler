package com.alquiler.demo.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Contrato;
import com.alquiler.demo.repositories.ContratoRepository;
import com.alquiler.demo.services.ContratoService;

@Service
public class ContratoServiceImpl implements ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public List<Contrato> findAll() {
        return contratoRepository.findAll();
    }

    @Override
    public Optional<Contrato> findById(Integer id) {
        return contratoRepository.findById(id);
    }

    @Override
    public Contrato save(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    @Override
    public Contrato update(Integer id, Contrato contrato) {
        if (contratoRepository.existsById(id)) {
            contrato.setIdContrato(id);
            return contratoRepository.save(contrato);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        contratoRepository.deleteById(id);
    }
}
