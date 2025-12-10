package com.alquiler.demo.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Seguro;
import com.alquiler.demo.repositories.SeguroRepository;
import com.alquiler.demo.services.SeguroService;

@Service
public class SeguroServiceImpl implements SeguroService {

    @Autowired
    private SeguroRepository seguroRepository;

    @Override
    public List<Seguro> findAll() {
        return seguroRepository.findAll();
    }

    @Override
    public Optional<Seguro> findById(Integer id) {
        return seguroRepository.findById(id);
    }

    @Override
    public Seguro save(Seguro seguro) {
        return seguroRepository.save(seguro);
    }

    @Override
    public Seguro update(Integer id, Seguro seguro) {
        if (seguroRepository.existsById(id)) {
            seguro.setIdSeguro(id);
            return seguroRepository.save(seguro);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        seguroRepository.deleteById(id);
    }
}
