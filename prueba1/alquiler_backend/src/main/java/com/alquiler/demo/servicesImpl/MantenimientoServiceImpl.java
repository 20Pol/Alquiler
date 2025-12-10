package com.alquiler.demo.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Mantenimiento;
import com.alquiler.demo.repositories.MantenimientoRepository;
import com.alquiler.demo.services.MantenimientoService;

@Service
public class MantenimientoServiceImpl implements MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Override
    public List<Mantenimiento> findAll() {
        return mantenimientoRepository.findAll();
    }

    @Override
    public Optional<Mantenimiento> findById(Integer id) {
        return mantenimientoRepository.findById(id);
    }

    @Override
    public Mantenimiento save(Mantenimiento mantenimiento) {
        return mantenimientoRepository.save(mantenimiento);
    }

    @Override
    public Mantenimiento update(Integer id, Mantenimiento mantenimiento) {
        if (mantenimientoRepository.existsById(id)) {
            mantenimiento.setIdMantenimiento(id);
            return mantenimientoRepository.save(mantenimiento);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        mantenimientoRepository.deleteById(id);
    }
}
