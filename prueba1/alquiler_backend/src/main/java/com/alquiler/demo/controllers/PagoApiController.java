package com.alquiler.demo.controllers;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.demo.entities.Cliente;
import com.alquiler.demo.entities.Pago;
import com.alquiler.demo.entities.Reserva;
import com.alquiler.demo.repositories.ClienteRepository;
import com.alquiler.demo.services.PagoService;
import com.alquiler.demo.services.ReservaService;

@RestController
@RequestMapping("/api/pago")
@CrossOrigin(origins = "http://localhost:4200")
public class PagoApiController {

    private final PagoService pagoService;
    private final ReservaService reservaService;
    private final ClienteRepository clienteRepository;

    public PagoApiController(PagoService pagoService, ReservaService reservaService,
            ClienteRepository clienteRepository) {
        this.pagoService = pagoService;
        this.reservaService = reservaService;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<Pago> procesarPago(@RequestBody PagoRequest request) {
        try {
            // Buscar la reserva
            Reserva reserva = reservaService.findById(request.getIdReserva());
            if (reserva == null) {
                return ResponseEntity.notFound().build();
            }

            // Buscar el cliente por usuario ID
            Cliente cliente = clienteRepository.findByUsuario_IdUsuario(request.getIdUsuario()).orElse(null);
            if (cliente == null) {
                return ResponseEntity.badRequest().build();
            }

            // Crear el pago
            Pago pago = new Pago();
            pago.setFechaPago(LocalDate.now());
            pago.setMonto(request.getMonto());
            pago.setMetodoPago(request.getMetodoPago());
            pago.setTipoComprobante("BOLETA");
            pago.setReferencia(request.getReferencia());
            pago.setCliente(cliente);
            pago.setReserva(reserva);

            // Guardar el pago
            Pago savedPago = pagoService.save(pago);

            // Actualizar el estado de la reserva a PAGADO
            reserva.setEstado("PAGADO");
            reservaService.save(reserva);

            return ResponseEntity.ok(savedPago);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Clase interna para la solicitud de pago
    public static class PagoRequest {
        private Integer idReserva;
        private Integer idUsuario;
        private java.math.BigDecimal monto;
        private String metodoPago;
        private String referencia;

        public Integer getIdReserva() {
            return idReserva;
        }

        public void setIdReserva(Integer idReserva) {
            this.idReserva = idReserva;
        }

        public Integer getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(Integer idUsuario) {
            this.idUsuario = idUsuario;
        }

        public java.math.BigDecimal getMonto() {
            return monto;
        }

        public void setMonto(java.math.BigDecimal monto) {
            this.monto = monto;
        }

        public String getMetodoPago() {
            return metodoPago;
        }

        public void setMetodoPago(String metodoPago) {
            this.metodoPago = metodoPago;
        }

        public String getReferencia() {
            return referencia;
        }

        public void setReferencia(String referencia) {
            this.referencia = referencia;
        }
    }
}
