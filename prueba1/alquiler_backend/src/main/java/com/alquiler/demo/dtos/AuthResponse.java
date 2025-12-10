package com.alquiler.demo.dtos;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String username;
    private String role;
    private Integer id;
}
