import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from '../../componentes/navbar.component';

@Component({
  selector: 'app-catalogo',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, NavbarComponent],
  templateUrl: './catalogo.component.html',
  styleUrls: ['./catalogo.component.css']
})
export class CatalogoComponent implements OnInit {
  vehiculos: any[] = [];
  vehiculosFiltrados: any[] = [];
  loading: boolean = true;
  error: string = '';

  // Filtros
  filtro = {
    marca: '',
    tipoVehiculo: '',
    anio: '',
    precioMax: null
  };

  // Listas para dropdowns
  marcas: string[] = [];
  tipos: string[] = [];
  anios: number[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.cargarVehiculos();
  }

  cargarVehiculos() {
    this.http.get<any[]>('http://localhost:8080/api/vehiculos')
      .subscribe({
        next: (data) => {
          this.vehiculos = data;
          this.vehiculosFiltrados = data;
          this.extraerOpcionesFiltro();
          this.loading = false;
        },
        error: (err) => {
          console.error('Error cargando vehículos', err);
          this.error = 'No se pudieron cargar los vehículos. Intente nuevamente más tarde.';
          this.loading = false;
        }
      });
  }

  extraerOpcionesFiltro() {
    this.marcas = [...new Set(this.vehiculos.map(v => v.marca))];
    this.tipos = [...new Set(this.vehiculos.map(v => v.tipoVehiculo))];
    this.anios = [...new Set(this.vehiculos.map(v => v.anio))].sort((a, b) => b - a);
  }

  filtrar() {
    this.vehiculosFiltrados = this.vehiculos.filter(v => {
      const cumpleMarca = !this.filtro.marca || v.marca === this.filtro.marca;
      const cumpleTipo = !this.filtro.tipoVehiculo || v.tipoVehiculo === this.filtro.tipoVehiculo;
      const cumpleAnio = !this.filtro.anio || v.anio.toString() === this.filtro.anio.toString();
      const cumplePrecio = !this.filtro.precioMax || v.precioDiario <= this.filtro.precioMax;

      return cumpleMarca && cumpleTipo && cumpleAnio && cumplePrecio;
    });
  }

  limpiar() {
    this.filtro = {
      marca: '',
      tipoVehiculo: '',
      anio: '',
      precioMax: null
    };
    this.vehiculosFiltrados = this.vehiculos;
  }
}