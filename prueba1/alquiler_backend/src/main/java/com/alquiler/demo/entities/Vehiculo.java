package com.alquiler.demo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    private String placa;
    private String marca;
    private String modelo;
    private Integer anio;
    private String color;
    private String estado;
    private Integer asientos;
    private double precioDiario;
    private String combustible;
    private String transmision;

    @Column(name = "tipo_vehiculo")
    private String tipoVehiculo;

    @Column(name = "kilometraje_actual")
    private Integer kilometrajeActual;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    @JsonBackReference("sucursal-vehiculos")
    private Sucursal sucursal;

    @OneToMany(mappedBy = "vehiculo")
    @JsonIgnoreProperties({ "vehiculo" })
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "vehiculo")
    @JsonManagedReference("vehiculo-mantenimientos")
    private List<Mantenimiento> mantenimientos;

    @OneToMany(mappedBy = "vehiculo")
    @JsonManagedReference("vehiculo-seguros")
    private List<Seguro> seguros;

    @Column(name = "imagen_url")
    private String imagenUrl;

    private String descripcion;

}
