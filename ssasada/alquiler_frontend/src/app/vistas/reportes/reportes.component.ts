import { Component } from '@angular/core';

@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css']
})
export class ReportesComponent {

  resumen = [
    { titulo: 'Total Clientes', valor: 150 },
    { titulo: 'Reservas Activas', valor: 25 },
    { titulo: 'Vehículos Disponibles', valor: 12 },
    { titulo: 'Pagos Realizados', valor: 47 }
  ];

  listaReservas: any[] = [];

  listaPagos: any[] = [];

  listaVehiculos: any[] = [];

}

