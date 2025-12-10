import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReservaService } from '../../servicios/reserva.service';
import { Reserva } from '../../clases/reserva';

@Component({
  selector: 'app-lista-alquileres',
  standalone: true,
  templateUrl: './lista-alquileres.component.html',
  styleUrls: ['./lista-alquileres.component.css'],
  imports: [CommonModule, FormsModule]
})
export class ListaAlquileresComponent implements OnInit {

  reservas: Reserva[] = [];
  reservasFiltradas: Reserva[] = [];
  filtroEstado: string = '';

  estados = ['Pendiente', 'Confirmada', 'En Curso', 'Completada', 'Cancelada']

  constructor(private reservaService: ReservaService) {}

  ngOnInit() {
    this.cargarReservas();
  }

  // Cargar todas las reservas desde el backend
  cargarReservas() {
    this.reservaService.getReservas().subscribe((data: Reserva[]) => {
      this.reservas = data;
      this.reservasFiltradas = data; // inicialmente mostrar todas
    }, error => {
      console.error('Error al cargar reservas', error);
    });
  }

  // Filtrar reservas por estado
  filtrarReservas() {
    if (!this.filtroEstado || this.filtroEstado.trim() === '') {
      this.reservasFiltradas = this.reservas;
      return;
    }
    this.reservasFiltradas = this.reservas.filter(r => r.estado === this.filtroEstado);
  }



  // Eliminar reserva
  eliminarReserva(reserva: Reserva) {
    if (confirm(`¿Seguro que deseas eliminar la reserva #${reserva.idReserva}?`)) {
      this.reservaService.eliminarReserva(reserva.idReserva).subscribe({
        next: () => {
          this.reservas = this.reservas.filter(r => r.idReserva !== reserva.idReserva);
          this.filtrarReservas();
          console.log('Reserva eliminada');
        },
        error: err => console.error('Error al eliminar reserva', err)
      });
    }
  }

  actualizarEstado(reserva: Reserva) {
  this.reservaService.actualizarReserva(reserva.idReserva, reserva.estado)
    .subscribe({
      next: (res) => {
        console.log(`Reserva #${reserva.idReserva} actualizada correctamente`);
        this.cargarReservas(); // refresca la lista
      },
      error: (err) => console.error('Error al actualizar el estado', err)
    });
}
}
