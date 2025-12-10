package com.alquiler.demo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "licencia_conducir")
    private String licenciaConducir;

    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference("cliente-reservas")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference("cliente-pagos")
    private List<Pago> pagos;
}
