package com.alquiler.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.demo.config.JwtUtils;
import com.alquiler.demo.dtos.AuthResponse;
import com.alquiler.demo.dtos.LoginRequest;
import com.alquiler.demo.dtos.RegisterRequest;
import com.alquiler.demo.entities.Cliente;
import com.alquiler.demo.entities.Rol;
import com.alquiler.demo.entities.Usuario;
import com.alquiler.demo.repositories.ClienteRepository;
import com.alquiler.demo.repositories.RolRepository;
import com.alquiler.demo.repositories.UsuarioRepository;
import com.alquiler.demo.services.CustomUserDetailsService;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager,
            CustomUserDetailsService userDetailsService,
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            ClienteRepository clienteRepository,
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        System.out.println("Intento de login para usuario: " + request.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtUtils.generateToken(userDetails);

        // Obtener el rol del usuario para enviarlo al frontend
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        String rol = usuario.getRol().getNombreRol();

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUsername(userDetails.getUsername());
        response.setRole(rol);
        response.setId(usuario.getIdUsuario());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegisterRequest request) {
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado(Usuario.EstadoUsuario.ACTIVO);

        // Asignar rol por defecto (CLIENTE)
        // Nota: Asegurarse de que el rol CLIENTE exista en la base de datos
        Rol rolCliente = rolRepository.findByNombreRol("CLIENTE")
                .orElseThrow(() -> new RuntimeException("Error: Rol CLIENTE no encontrado."));
        usuario.setRol(rolCliente);

        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        // Crear Cliente asociado
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setDni(request.getDni());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion(request.getDireccion());
        cliente.setEmail(request.getEmail());
        cliente.setLicenciaConducir(request.getLicenciaConducir());
        cliente.setUsuario(nuevoUsuario);

        clienteRepository.save(cliente);

        java.util.Map<String, String> response = new java.util.HashMap<>();
        response.put("message", "Usuario registrado exitosamente");
        return ResponseEntity.ok(response);
    }
}