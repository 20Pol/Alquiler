import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cliente-detalle',
  templateUrl: './cliente-detalle.component.html',
  styleUrls: ['./cliente-detalle.component.css']
})
export class ClienteDetalleComponent implements OnInit {

  cliente: any = null;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    // ⚠ Luego lo reemplazarás con un servicio que llame al backend
    this.cliente = {
      id: id,
      nombre: "Juan",
      apellido: "Pérez",
      dni: "12345678",
      telefono: "987654321",
      email: "juan.perez@example.com",
      direccion: "Av. Los Olivos 123",
      alquileres: [
        {
          vehiculo: { modelo: "Toyota Yaris" },
          fechaInicio: "2023-05-10",
          fechaFin: "2023-05-15"
        }
      ]
    };
  }
}
