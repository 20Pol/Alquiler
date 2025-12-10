import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard-program',
  templateUrl: './dashboard-program.component.html',
  styleUrls: ['./dashboard-program.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class DashboardProgramComponent {
  alquileresRecientes = [
    {
      id: '#1003',
      cliente: 'Carlos Ruiz',
      vehiculo: 'DEF-456 - Ford Focus',
      inicio: new Date('2025-10-14'),
      fin: new Date('2025-10-20'),
      estado: 'En Curso'
    },
    {
      id: '#1002',
      cliente: 'María López',
      vehiculo: 'XYZ-789 - Honda Civic',
      inicio: new Date('2025-10-12'),
      fin: new Date('2025-10-18'),
      estado: 'En Curso'
    },
    {
      id: '#1001',
      cliente: 'Juan Pérez',
      vehiculo: 'ABC-123 - Toyota Corolla',
      inicio: new Date('2025-10-10'),
      fin: new Date('2025-10-15'),
      estado: 'Completado'
    }
  ];
}