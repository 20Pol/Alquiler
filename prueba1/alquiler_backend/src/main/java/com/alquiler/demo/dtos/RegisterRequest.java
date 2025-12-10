package com.alquiler.demo.dtos;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private String licenciaConducir;
}
