package com.alquiler.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "Alquiler")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alquiler")
    private Integer idAlquiler;

    @Column(name = "fecha_inicio_real")
    private LocalDate fechaInicioReal;

    @Column(name = "fecha_fin_real")
    private LocalDate fechaFinReal;

    @Column(name = "kilometraje_inicio")
    private Integer kilometrajeInicio;

    @Column(name = "kilometraje_fin")
    private Integer kilometrajeFin;

    @Column(name = "costo_total")
    private BigDecimal costoTotal;

    @OneToOne
    @JoinColumn(name = "id_contrato")
    @JsonManagedReference("contrato-alquiler")
    private Contrato contrato;

    @OneToMany(mappedBy = "alquiler")
    @JsonIgnoreProperties("alquiler") // rompe ciclos
    private List<Pago> pagos;

    @OneToMany(mappedBy = "alquiler")
    @JsonIgnoreProperties("alquiler")
    private List<Danio> danos;
}
