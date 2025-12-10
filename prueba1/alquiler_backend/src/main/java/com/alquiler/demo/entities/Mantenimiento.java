package com.alquiler.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "Mantenimiento")
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mantenimiento")
    private Integer idMantenimiento;

    @Column(name = "fecha_mantenimiento")
    private LocalDate fechaMantenimiento;

    private String tipo;

    @Column(columnDefinition = "text")
    private String descripcion;

    private BigDecimal costo;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    @JsonBackReference("vehiculo-mantenimientos")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    @JsonBackReference(value = "empleado-mantenimientos")
    private Empleado empleado;
}
