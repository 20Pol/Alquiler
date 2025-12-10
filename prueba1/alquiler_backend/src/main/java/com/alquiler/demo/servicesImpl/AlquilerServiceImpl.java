package com.alquiler.demo.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Alquiler;
import com.alquiler.demo.repositories.AlquilerRepository;
import com.alquiler.demo.services.AlquilerService;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Override
    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    @Override
    public Optional<Alquiler> findById(Integer id) {
        return alquilerRepository.findById(id);
    }

    @Override
    public Alquiler save(Alquiler alquiler) {
        return alquilerRepository.save(alquiler);
    }

    @Override
    public Alquiler update(Integer id, Alquiler alquiler) {
        if (alquilerRepository.existsById(id)) {
            alquiler.setIdAlquiler(id);
            return alquilerRepository.save(alquiler);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        alquilerRepository.deleteById(id);
    }
}
