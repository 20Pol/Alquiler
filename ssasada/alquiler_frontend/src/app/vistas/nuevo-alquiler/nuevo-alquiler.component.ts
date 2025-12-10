import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // 👈 Importa para usar router-outlet si lo necesitas

@Component({
  selector: 'app-nuevo-alquiler',
  templateUrl: './nuevo-alquiler.component.html',
  styleUrls: ['./nuevo-alquiler.component.css'],
  imports: [RouterModule] // 👈 Obligatorio si usas rutas o router-outlet en este layout
})
export class NuevoAlquilerComponent {
  // Aquí puedes agregar lógica de formulario más adelante
}