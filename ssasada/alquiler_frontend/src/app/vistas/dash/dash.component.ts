import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CatalogoService } from '../../servicios/catalogo.service';
import { Catalogo } from '../../clases/catalogo';

@Component({
  selector: 'app-dash',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dash.component.html', 
  styleUrl: './dash.component.css'
})
export class DashComponent implements OnInit {
  vehiculos: Catalogo[] = [];
  nuevoVehiculo: any = {
    idVehiculo: null,
    placa: '',
    marca: '',
    modelo: '',
    anio: 2024,
    color: '',
    precioDiario: 0,
    estado: 'DISPONIBLE',
    kilometrajeActual: 0,
    tipoVehiculo: 'AUTO',
    imagenUrl: '',
    descripcion: '',
    asientos: 5,
    transmision: 'MECANICO',
    combustible: 'GASOLINA'
  };
  mostrarFormulario = false;

  constructor(private catalogoService: CatalogoService) { }

  ngOnInit(): void {
    this.cargarVehiculos();
  }

  cargarVehiculos() {
    this.catalogoService.getVehiculos().subscribe({
      next: (data) => {
        this.vehiculos = data;
        console.log('Vehiculos cargados:', this.vehiculos);
      },
      error: (err) => {
        console.error('Error cargando vehículos', err);
      }
    });
  }

  toggleFormulario() {
    this.mostrarFormulario = !this.mostrarFormulario;
    if (!this.mostrarFormulario) {
      this.limpiarFormulario();
    }
  }

  editarVehiculo(vehiculo: any) {
    this.nuevoVehiculo = { ...vehiculo };
    this.mostrarFormulario = true;
  }

  guardarVehiculo() {
    if (this.nuevoVehiculo.idVehiculo) {
      this.catalogoService.updateVehiculo(this.nuevoVehiculo.idVehiculo, this.nuevoVehiculo).subscribe({
        next: (res) => {
          alert('Vehículo actualizado correctamente');
          this.cargarVehiculos();
          this.toggleFormulario();
          this.limpiarFormulario();
        },
        error: (err) => {
          console.error('Error actualizando vehículo', err);
          alert('Error al actualizar vehículo');
        }
      });
    } else {
      this.catalogoService.createVehiculo(this.nuevoVehiculo).subscribe({
        next: (res) => {
          alert('Vehículo agregado correctamente');
          this.cargarVehiculos();
          this.toggleFormulario();
          this.limpiarFormulario();
        },
        error: (err) => {
          console.error('Error guardando vehículo', err);
          alert('Error al guardar vehículo');
        }
      });
    }
  }

  eliminarVehiculo(id: number) {
    console.log('Intentando eliminar vehiculo con ID:', id);
    if (!id) {
      console.error('ID inválido para eliminar');
      alert('Error: ID de vehículo no válido');
      return;
    }
    if (confirm('¿Estás seguro de eliminar este vehículo?')) {
      this.catalogoService.deleteVehiculo(id).subscribe({
        next: (res) => {
          alert('Vehículo eliminado');
          this.cargarVehiculos();
        },
        error: (err) => {
          console.error('Error eliminando vehículo', err);
          alert('Error al eliminar vehículo');
        }
      });
    }
  }

  limpiarFormulario() {
    this.nuevoVehiculo = {
      idVehiculo: null,
      placa: '',
      marca: '',
      modelo: '',
      anio: 2024,
      color: '',
      precioDiario: 0,
      estado: 'DISPONIBLE',
      kilometrajeActual: 0,
      tipoVehiculo: 'AUTO',
      imagenUrl: '',
      descripcion: '',
      asientos: 5,
      transmision: 'MECANICO',
      combustible: 'GASOLINA'
    };
  }
}
