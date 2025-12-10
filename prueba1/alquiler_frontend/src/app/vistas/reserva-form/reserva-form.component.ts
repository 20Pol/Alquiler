import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CatalogoService } from '../../servicios/catalogo.service';
import { ReservaService } from '../../servicios/reserva.service';
import { NavbarComponent } from '../../componentes/navbar.component';
import { AuthService } from '../../servicios/auth.service';
import { PerfilService } from '../../servicios/perfil.service';

declare var bootstrap: any;
@Component({
  imports: [CommonModule, FormsModule, RouterModule, NavbarComponent],
  selector: 'app-reserva',
  standalone: true,
  templateUrl: './reserva-form.component.html',
  styleUrls: ['./reserva-form.component.css']
})
export class ReservaComponent implements OnInit {

  vehiculo: any;
  fechaInicio: string = '';
  fechaFin: string = '';

  metodoEntrega = 'tienda';
  direccion = '';

  costoTotal: number | null = null;
  dias: number = 0;

  errorFecha = false;
  idCliente: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private catalogoService: CatalogoService,
    private reservaService: ReservaService,
    private authService: AuthService,
    private perfilService: PerfilService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.catalogoService.getVehiculoPorId(id).subscribe(v => {
        this.vehiculo = v;
      });
    }

    // Obtener ID del cliente logueado
    const currentUser = this.authService.currentUserValue;
    console.log('CurrentUser en Reserva:', currentUser);

    const userId = currentUser.id || currentUser.idUsuario;

    if (currentUser && userId) {
      this.perfilService.getClientePorId(userId).subscribe({
        next: (cliente) => {
          console.log('Cliente recuperado:', cliente);
          if (cliente && cliente.idCliente) {
            this.idCliente = cliente.idCliente;
            console.log('ID Cliente establecido:', this.idCliente);
          } else {
            console.warn('El usuario no tiene un perfil de cliente asociado o idCliente es nulo.');
          }
        },
        error: (err) => console.error('Error obteniendo perfil cliente', err)
      });
    } else {
      console.warn('No hay usuario logueado o falta idUsuario');
    }
  }

  calcularCosto() {
    if (!this.fechaInicio || !this.fechaFin) {
      this.costoTotal = null;
      return;
    }

    const inicio = new Date(this.fechaInicio);
    const fin = new Date(this.fechaFin);

    const diff = (fin.getTime() - inicio.getTime()) / (1000 * 60 * 60 * 24);

    if (diff <= 0) {
      this.errorFecha = true;
      this.costoTotal = null;
      return;
    }

    this.errorFecha = false;
    this.dias = diff;
    this.costoTotal = diff * this.vehiculo.precioDiario;
  }

  confirmarReserva() {
    if (!this.idCliente) {
      alert('Debes iniciar sesión para reservar.');
      this.router.navigate(['/login']);
      return;
    }
    this.crearReserva();
  }

  crearReserva() {
    if (this.errorFecha) {
      return;
    }

    this.calcularCosto();

    const reserva = {
      fechaInicio: this.fechaInicio,
      fechaFin: this.fechaFin,
      metodoEntrega: this.metodoEntrega,
      direccionEntrega: this.metodoEntrega === 'domicilio' ? this.direccion : null,
      vehiculo: { idVehiculo: this.vehiculo.idVehiculo },
      cliente: { idCliente: this.idCliente },
      costoEstimado: this.costoTotal,
      estado: 'reservado'
    };

    this.reservaService.crearReserva(reserva).subscribe({
      next: () => {
        // Abrir modal de éxito
        const modal = new bootstrap.Modal(document.getElementById('successModal'));
        modal.show();
      },
      error: (err) => {
        console.error('Error creando reserva', err);
        alert('Hubo un error al crear la reserva.');
      }
    });
  }

  cerrarYVolver() {
    // Cerrar modal manualmente
    const modalElement = document.getElementById('successModal');
    const modal = bootstrap.Modal.getInstance(modalElement);
    modal.hide();

    // Redirigir al inicio
    this.router.navigate(['/']);
  }
}