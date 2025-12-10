package com.alquiler.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alquiler.demo.entities.Rol;
import com.alquiler.demo.entities.Usuario;
import com.alquiler.demo.repositories.RolRepository;
import com.alquiler.demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final com.alquiler.demo.repositories.ClienteRepository clienteRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository,
            com.alquiler.demo.repositories.ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario updateRol(Integer idUsuario, String nombreRol) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepository.findByNombreRol(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setRol(rol);
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
        // Buscar si hay un cliente asociado a este usuario
        com.alquiler.demo.entities.Cliente cliente = clienteRepository.findByUsuario_IdUsuario(id).orElse(null);

        if (cliente != null) {
            // Si el cliente tiene reservas o pagos, esto podría fallar si no hay cascada.
            // Para este caso simple, intentamos eliminar el cliente.
            clienteRepository.delete(cliente);
        }

        usuarioRepository.deleteById(id);
    }
}
