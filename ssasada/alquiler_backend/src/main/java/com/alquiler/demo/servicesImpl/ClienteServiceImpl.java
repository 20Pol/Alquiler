	package com.alquiler.demo.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Cliente;
import com.alquiler.demo.repositories.ClienteRepository;
import com.alquiler.demo.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

  
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }


	@Override
	public Cliente obtenerPorId(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}
}
