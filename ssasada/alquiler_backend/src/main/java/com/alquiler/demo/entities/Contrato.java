package com.alquiler.demo.entities;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Integer idContrato;

    @Column(name = "fecha_firma")
    private LocalDate fechaFirma;

    @Column(name = "terminos_condiciones", columnDefinition = "text")
    private String terminosCondiciones;

    @OneToOne
    @JoinColumn(name = "id_reserva")
    @JsonManagedReference("reserva-contrato")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    @JsonBackReference(value = "empleado-contratos")
    private Empleado empleado;
    
    @OneToOne(mappedBy = "contrato")
    @JsonBackReference("contrato-alquiler")
    private Alquiler alquiler;;
}
