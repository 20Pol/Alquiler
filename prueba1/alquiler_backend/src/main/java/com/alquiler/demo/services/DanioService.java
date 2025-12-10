package com.alquiler.demo.services;

import com.alquiler.demo.entities.Danio;
import java.util.List;
import java.util.Optional;

public interface DanioService {
    List<Danio> findAll();
    Optional<Danio> findById(Integer id);
    Danio save(Danio danio);
    Danio update(Integer id, Danio danio);
    void delete(Integer id);
}
