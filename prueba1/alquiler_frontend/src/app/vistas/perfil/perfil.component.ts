import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PerfilService } from '../../servicios/perfil.service';
import { AuthService } from '../../servicios/auth.service';

@Component({
  imports: [CommonModule, FormsModule, RouterModule],
  selector: 'app-perfil',
  standalone: true,
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent {
  cliente: any = {};
  reservas: any[] = [];
  pagos: any[] = [];
  clienteId: number = 1;

  // Modal states
  showVerReservaModal: boolean = false;
  showPagoModal: boolean = false;
  showCancelarModal: boolean = false;

  // Selected reservation for modals
  reservaSeleccionada: any = null;

  // Payment form
  metodoPagoSeleccionado: string = 'Yape';
  procesandoPago: boolean = false;
  mensajePago: string = '';
  pagoExitoso: boolean = false;

  // Yape fields (simulated)
  yapeNumero: string = '';
  yapeCodigo: string = '';

  // Card fields (simulated)
  tarjetaNumero: string = '';
  tarjetaNombre: string = '';
  tarjetaExpiracion: string = '';
  tarjetaCVV: string = '';

  // Transferencia fields (simulated)
  transferenciaReferencia: string = '';

  constructor(private perfilService: PerfilService, private authService: AuthService) { }

  ngOnInit(): void {
    const currentUser = this.authService.currentUserValue;
    if (currentUser && currentUser.id) {
      this.clienteId = currentUser.id;
      this.cargarPerfil();
      this.cargarReservas();
      this.cargarPagos();
    }
  }

  cargarPerfil(): void {
    this.perfilService.getClientePorId(this.clienteId)
      .subscribe({
        next: data => {
          this.cliente = data || {};
        },
        error: err => console.error('Error cargando cliente', err)
      });
  }

  cargarReservas(): void {
    this.perfilService.getReservasCliente(this.clienteId)
      .subscribe({
        next: data => this.reservas = data,
        error: err => console.error('Error cargando reservas', err)
      });
  }

  cargarPagos(): void {
    this.perfilService.getPagosCliente(this.clienteId)
      .subscribe({
        next: data => this.pagos = data,
        error: err => console.error('Error cargando pagos', err)
      });
  }

  // ======= VER RESERVA MODAL =======
  verReserva(reserva: any): void {
    this.reservaSeleccionada = reserva;
    this.showVerReservaModal = true;
  }

  cerrarVerReservaModal(): void {
    this.showVerReservaModal = false;
    this.reservaSeleccionada = null;
  }

  // ======= PAGAR RESERVA MODAL =======
  abrirPagoModal(reserva: any): void {
    this.reservaSeleccionada = reserva;
    this.metodoPagoSeleccionado = 'Yape';
    this.mensajePago = '';
    this.pagoExitoso = false;
    this.limpiarCamposPago();
    this.showPagoModal = true;
  }

  limpiarCamposPago(): void {
    this.yapeNumero = '';
    this.yapeCodigo = '';
    this.tarjetaNumero = '';
    this.tarjetaNombre = '';
    this.tarjetaExpiracion = '';
    this.tarjetaCVV = '';
    this.transferenciaReferencia = '';
  }

  cerrarPagoModal(): void {
    this.showPagoModal = false;
    this.reservaSeleccionada = null;
    this.mensajePago = '';
    this.pagoExitoso = false;
    this.limpiarCamposPago();
  }

  validarFormularioPago(): boolean {
    if (this.metodoPagoSeleccionado === 'Yape') {
      if (!this.yapeNumero || this.yapeNumero.length < 9) {
        this.mensajePago = 'Ingrese un número de Yape válido (9 dígitos)';
        return false;
      }
      if (!this.yapeCodigo || this.yapeCodigo.length < 4) {
        this.mensajePago = 'Ingrese el código de aprobación';
        return false;
      }
    } else if (this.metodoPagoSeleccionado === 'Tarjeta') {
      if (!this.tarjetaNumero || this.tarjetaNumero.replace(/\s/g, '').length < 16) {
        this.mensajePago = 'Ingrese un número de tarjeta válido';
        return false;
      }
      if (!this.tarjetaNombre) {
        this.mensajePago = 'Ingrese el nombre del titular';
        return false;
      }
      if (!this.tarjetaExpiracion || this.tarjetaExpiracion.length < 5) {
        this.mensajePago = 'Ingrese la fecha de expiración (MM/AA)';
        return false;
      }
      if (!this.tarjetaCVV || this.tarjetaCVV.length < 3) {
        this.mensajePago = 'Ingrese el CVV';
        return false;
      }
    } else if (this.metodoPagoSeleccionado === 'Transferencia') {
      if (!this.transferenciaReferencia || this.transferenciaReferencia.length < 6) {
        this.mensajePago = 'Ingrese el número de operación';
        return false;
      }
    }
    return true;
  }

  generarReferencia(): string {
    const timestamp = Date.now().toString().slice(-8);
    if (this.metodoPagoSeleccionado === 'Yape') {
      return `YAPE-${this.yapeNumero.slice(-4)}-${timestamp}`;
    } else if (this.metodoPagoSeleccionado === 'Tarjeta') {
      return `CARD-${this.tarjetaNumero.replace(/\s/g, '').slice(-4)}-${timestamp}`;
    } else {
      return `TRANS-${this.transferenciaReferencia}`;
    }
  }

  confirmarPago(): void {
    if (!this.reservaSeleccionada) return;

    // Validar formulario
    if (!this.validarFormularioPago()) {
      this.pagoExitoso = false;
      return;
    }

    this.procesandoPago = true;
    this.mensajePago = '';

    const pagoData = {
      idReserva: this.reservaSeleccionada.idReserva,
      idUsuario: this.clienteId,
      monto: this.reservaSeleccionada.costoEstimado || 0,
      metodoPago: this.metodoPagoSeleccionado,
      referencia: this.generarReferencia()
    };

    this.perfilService.procesarPago(pagoData).subscribe({
      next: (response) => {
        this.procesandoPago = false;
        this.pagoExitoso = true;
        this.mensajePago = '¡Pago procesado exitosamente!';
        this.cargarReservas();
        this.cargarPagos();

        // Cerrar modal después de 2 segundos
        setTimeout(() => {
          this.cerrarPagoModal();
        }, 2000);
      },
      error: (err) => {
        this.procesandoPago = false;
        this.pagoExitoso = false;
        this.mensajePago = 'Error al procesar el pago. Intente nuevamente.';
        console.error('Error procesando pago', err);
      }
    });
  }

  // ======= CANCELAR RESERVA MODAL =======
  cancelarReserva(reserva: any): void {
    this.reservaSeleccionada = reserva;
    this.showCancelarModal = true;
  }

  cerrarCancelarModal(): void {
    this.showCancelarModal = false;
    this.reservaSeleccionada = null;
  }

  confirmarCancelacion(): void {
    if (!this.reservaSeleccionada) return;

    this.perfilService.cancelarReserva(this.reservaSeleccionada.idReserva).subscribe({
      next: () => {
        this.cargarReservas();
        this.cerrarCancelarModal();
      },
      error: (err) => {
        console.error('Error cancelando reserva', err);
        alert('Error al cancelar la reserva. Intente nuevamente.');
      }
    });
  }

  // ======= HELPERS =======
  calcularDias(): number {
    if (!this.reservaSeleccionada?.fechaInicio || !this.reservaSeleccionada?.fechaFin) return 0;
    const inicio = new Date(this.reservaSeleccionada.fechaInicio);
    const fin = new Date(this.reservaSeleccionada.fechaFin);
    const diff = Math.abs(fin.getTime() - inicio.getTime());
    return Math.ceil(diff / (1000 * 60 * 60 * 24)) || 1;
  }

  getVehiculoInfo(reserva: any): string {
    if (reserva.vehiculo) {
      return `${reserva.vehiculo.marca || ''} ${reserva.vehiculo.modelo || ''}`.trim() || 'Vehículo no disponible';
    }
    return 'Vehículo no disponible';
  }

  isPagado(reserva: any): boolean {
    return reserva.estado?.toUpperCase() === 'PAGADO';
  }

  // Formatear número de tarjeta mientras se escribe
  formatearTarjeta(event: any): void {
    let value = event.target.value.replace(/\s/g, '').replace(/\D/g, '');
    let formatted = '';
    for (let i = 0; i < value.length && i < 16; i++) {
      if (i > 0 && i % 4 === 0) formatted += ' ';
      formatted += value[i];
    }
    this.tarjetaNumero = formatted;
  }

  // Formatear fecha de expiración
  formatearExpiracion(event: any): void {
    let value = event.target.value.replace(/\D/g, '');
    if (value.length >= 2) {
      value = value.slice(0, 2) + '/' + value.slice(2, 4);
    }
    this.tarjetaExpiracion = value;
  }
}
