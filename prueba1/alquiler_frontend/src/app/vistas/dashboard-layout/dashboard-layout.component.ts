import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // 👈 Importa RouterModule

@Component({
  selector: 'app-dashboard-layout',
  templateUrl: './dashboard-layout.component.html',
  styleUrls: ['./dashboard-layout.component.css'],
  imports: [RouterModule] // 👈 Añade esto → ¡clave en Angular 19.2!
})
export class DashboardLayoutComponent {}