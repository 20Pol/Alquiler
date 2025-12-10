package com.alquiler.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Dano")
public class Danio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dano")
    private Integer idDano;

    @Column(columnDefinition = "text")
    private String descripcion;

    @Column(name = "costo_estimado")
    private BigDecimal costoEstimado;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "id_alquiler")
    @JsonIgnoreProperties("danos")
    private Alquiler alquiler;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    @JsonBackReference(value = "empleado-danos")
    private Empleado empleado;
}
