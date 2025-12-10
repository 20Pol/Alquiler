import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // 👈 Importa CommonModule

@Component({
  selector: 'app-lista-alquileres',
  templateUrl: './lista-alquileres.component.html',
  styleUrls: ['./lista-alquileres.component.css'],
  standalone: true,
  imports: [CommonModule] // 👈 Añade esto → ¡clave!
})
export class ListaAlquileresComponent {
  alquileres = [
    {
      id: '#1001',
      cliente: 'Juan Pérez',
      vehiculo: 'ABC-123 - Toyota Corolla',
      inicio: new Date('2025-10-10'),
      fin: new Date('2025-10-15'),
      kmInicio: 12000,
      kmFin: 12500,
      costoTotal: 450.00,
      estado: 'Completado'
    },
    {
      id: '#1002',
      cliente: 'María López',
      vehiculo: 'XYZ-789 - Honda Civic',
      inicio: new Date('2025-10-12'),
      fin: new Date('2025-10-18'),
      kmInicio: 8000,
      kmFin: null,
      costoTotal: 320.00,
      estado: 'En Curso'
    },
    {
      id: '#1003',
      cliente: 'Carlos Ruiz',
      vehiculo: 'DEF-456 - Ford Focus',
      inicio: new Date('2025-10-14'),
      fin: new Date('2025-10-20'),
      kmInicio: 15000,
      kmFin: null,
      costoTotal: 280.00,
      estado: 'En Curso'
    }
  ];
}