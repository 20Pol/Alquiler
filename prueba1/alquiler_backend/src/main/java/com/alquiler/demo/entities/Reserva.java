package com.alquiler.demo.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Integer idReserva;

    @CreationTimestamp
    @Column(name = "fecha_reserva", updatable = false)
    private LocalDateTime fechaReserva;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "costo_estimado")
    private Double costoEstimado;

    private String estado;

    @Column(name = "metodo_entrega")
    private String metodoEntrega;

    @Column(name = "direccion_entrega")
    private String direccionEntrega;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonBackReference("cliente-reservas")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    @JsonIgnoreProperties({ "reservas", "mantenimientos", "seguros", "sucursal" })
    private Vehiculo vehiculo;

    @OneToOne(mappedBy = "reserva")
    @JsonBackReference("reserva-contrato")
    private Contrato contrato;
}
