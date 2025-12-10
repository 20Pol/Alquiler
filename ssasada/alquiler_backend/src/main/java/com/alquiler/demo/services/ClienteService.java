package com.alquiler.demo.services;

import java.util.List;

import com.alquiler.demo.entities.Cliente;

public interface ClienteService {
    List<Cliente> findAll();
    Cliente obtenerPorId(Integer id);

}
