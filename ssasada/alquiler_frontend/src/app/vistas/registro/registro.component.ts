import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../servicios/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-registro-cliente',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {

  constructor(private router: Router, private authService: AuthService) { }

  successMessage: string = '';
  errorMessage: string = '';

  cliente = {
    username: '',
    password: '',
    nombre: '',
    apellido: '',
    dni: '',
    telefono: '',
    email: '',
    direccion: '',
    licenciaConducir: ''
  };

  registrarCliente() {
    this.authService.registro(this.cliente).subscribe({
      next: (response) => {
        console.log('Registro exitoso', response);
        this.successMessage = 'Registro exitoso. Redirigiendo al login...';
        this.errorMessage = '';
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error) => {
        console.error('Error en registro:', error);
        if (typeof error.error === 'string') {
          this.errorMessage = error.error;
        } else if (error.error && typeof error.error === 'object') {
          this.errorMessage = error.error.message || error.error.error || JSON.stringify(error.error);
        } else {
          this.errorMessage = 'Error desconocido en el registro';
        }
        this.successMessage = '';
      }
    });
  }

  goToRegresar() {
    this.router.navigate(['/login']);
  }
}

