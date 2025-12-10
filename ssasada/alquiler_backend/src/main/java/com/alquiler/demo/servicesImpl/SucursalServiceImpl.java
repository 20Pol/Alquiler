package com.alquiler.demo.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Sucursal;
import com.alquiler.demo.repositories.SucursalRepository;
import com.alquiler.demo.services.SucursalService;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    @Override
    public Optional<Sucursal> findById(Integer id) {
        return sucursalRepository.findById(id);
    }

    @Override
    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal update(Integer id, Sucursal sucursal) {
        if (sucursalRepository.existsById(id)) {
            sucursal.setIdSucursal(id);
            return sucursalRepository.save(sucursal);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        sucursalRepository.deleteById(id);
    }
}
