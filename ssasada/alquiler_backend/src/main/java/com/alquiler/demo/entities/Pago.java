package com.alquiler.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "Pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    private BigDecimal monto;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Column(name = "tipo_comprobante")
    private String tipoComprobante;

    @ManyToOne
    @JoinColumn(name = "id_alquiler")
    private Alquiler alquiler;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonBackReference("cliente-pagos")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    @JsonBackReference("reserva-pagos")
    private Reserva reserva;
}
