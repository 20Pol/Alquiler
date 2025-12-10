import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-gestion-pagos',
  templateUrl: './gestion-pagos.component.html',
  styleUrls: ['./gestion-pagos.component.css'],
  standalone: true,
  imports: [CommonModule] // 👈 Para ngClass, ngFor
})
export class GestionPagosComponent {
  pagos = [
    {
      id: '#P001',
      comprobante: '#C1001',
      cliente: 'Juan Pérez',
      monto: 450.00,
      metodo: 'efectivo',
      fecha: new Date('2025-10-15T14:35:00'),
      estado: 'Completado'
    },
    {
      id: '#P002',
      comprobante: '#C1002',
      cliente: 'María López',
      monto: 320.00,
      metodo: 'tarjeta',
      fecha: new Date('2025-10-14T18:20:00'),
      estado: 'Completado'
    },
    {
      id: '#P003',
      comprobante: '#C1003',
      cliente: 'Carlos Ruiz',
      monto: 140.00,
      metodo: 'transferencia',
      fecha: new Date('2025-10-15T09:15:00'),
      estado: 'Pendiente'
    }
  ];
}