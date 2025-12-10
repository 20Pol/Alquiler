import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { VehiculoService } from '../vehiculo/vehiculo.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  imports: [CommonModule, FormsModule, RouterModule],
  selector: 'app-vehiculo-detalle',
  templateUrl: './vehiculo-detalle.component.html',
  styleUrls: ['./vehiculo-detalle.component.css']
})
export class VehiculoDetalleComponent implements OnInit {

  vehiculo: any;

  constructor(
    private route: ActivatedRoute,
    private vehiculoService: VehiculoService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.vehiculoService.getVehiculoById(+id).subscribe(data => {
        this.vehiculo = data;
      });
    }
  }
}
