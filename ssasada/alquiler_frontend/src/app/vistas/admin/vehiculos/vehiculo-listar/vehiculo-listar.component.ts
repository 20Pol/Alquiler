import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

interface Vehiculo {
  id: number;
  imagen?: string;
  marca: string;
  modelo: string;
  anio: number;
  precioPorDia: number;
  disponible: boolean;
}

@Component({
  imports: [CommonModule, FormsModule, RouterModule],
  selector: 'app-vehiculo-listar',
  templateUrl: './vehiculo-listar.component.html',
  styleUrls: ['./vehiculo-listar.component.css']
})
export class VehiculoListarComponent implements OnInit {
  vehiculos: Vehiculo[] = [];

  constructor() { }

  ngOnInit(): void {
    // Aquí puedes traer los vehículos desde tu API
    this.vehiculos = [
      { id: 1, marca: 'Toyota', modelo: 'Corolla', anio: 2020, precioPorDia: 40, disponible: true, imagen: 'toyota.jpg' },
      { id: 2, marca: 'Ford', modelo: 'Focus', anio: 2019, precioPorDia: 35, disponible: false }
      // Agrega más ejemplos o trae desde API
    ];
  }

  eliminarVehiculo(id: number): void {
    if (confirm('¿Seguro que deseas eliminar este vehículo?')) {
      this.vehiculos = this.vehiculos.filter(v => v.id !== id);
      // Aquí podrías llamar a tu API para eliminar en la base de datos
    }
  }
}
