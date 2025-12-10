package com.alquiler.demo.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Danio;
import com.alquiler.demo.repositories.DanioRepository;
import com.alquiler.demo.services.DanioService;

@Service
public class DanioServiceImpl implements DanioService {

    @Autowired
    private DanioRepository danioRepository;

    @Override
    public List<Danio> findAll() {
        return danioRepository.findAll();
    }

    @Override
    public Optional<Danio> findById(Integer id) {
        return danioRepository.findById(id);
    }

    @Override
    public Danio save(Danio danio) {
        return danioRepository.save(danio);
    }

    @Override
    public Danio update(Integer id, Danio danio) {
        if (danioRepository.existsById(id)) {
            danio.setIdDano(id);
            return danioRepository.save(danio);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        danioRepository.deleteById(id);
    }
}
