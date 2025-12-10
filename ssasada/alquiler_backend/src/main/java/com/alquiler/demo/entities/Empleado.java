package com.alquiler.demo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "Empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    private String nombre;
    private String apellido;
    private String cargo;
    private String telefono;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    @JsonBackReference(value = "sucursal-empleados")
    private Sucursal sucursal;


    @OneToMany(mappedBy = "empleado")
    @JsonManagedReference(value = "empleado-contratos")
    private List<Contrato> contratos;

    @OneToMany(mappedBy = "empleado")
    @JsonManagedReference(value = "empleado-mantenimientos")
    private List<Mantenimiento> mantenimientos;

    @OneToMany(mappedBy = "empleado")
    @JsonManagedReference(value = "empleado-danos")
    private List<Danio> danos;
    
}
