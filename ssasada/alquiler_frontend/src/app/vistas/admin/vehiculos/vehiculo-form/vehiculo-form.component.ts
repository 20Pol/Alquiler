import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vehiculo-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './vehiculo-form.component.html',
  styleUrls: ['./vehiculo-form.component.css']
})
export class VehiculoFormComponent {
  vehiculoForm: FormGroup;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.vehiculoForm = this.fb.group({
      marca: ['', Validators.required],
      modelo: ['', Validators.required],
      anio: ['', [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear() + 1)]],
      color: ['', Validators.required],
      placa: ['', Validators.required],
      precioDiario: ['', [Validators.required, Validators.min(0)]],
      estado: ['DISPONIBLE', Validators.required],
      asientos: ['', [Validators.required, Validators.min(1)]],
      combustible: ['', Validators.required],
      transmision: ['', Validators.required],
      tipoVehiculo: ['', Validators.required],
      imagen: ['']
    });
  }

  onSubmit() {
    if (this.vehiculoForm.valid) {
      this.http.post('http://localhost:8080/api/vehiculos', this.vehiculoForm.value)
        .subscribe({
          next: (response) => {
            console.log('Vehículo creado', response);
            this.successMessage = 'Vehículo creado exitosamente';
            this.errorMessage = '';
            this.vehiculoForm.reset();
            setTimeout(() => this.router.navigate(['/dashboard/lista-alquileres']), 2000); // Redirect to list
          },
          error: (error) => {
            console.error('Error creando vehículo', error);
            this.errorMessage = 'Error al crear el vehículo';
            this.successMessage = '';
          }
        });
    } else {
      this.errorMessage = 'Por favor complete todos los campos requeridos';
    }
  }
}
