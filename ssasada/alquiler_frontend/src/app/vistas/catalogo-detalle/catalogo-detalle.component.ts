import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CatalogoService } from '../../servicios/catalogo.service';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../../componentes/navbar.component';

@Component({
    selector: 'app-catalogo-detalle',
    standalone: true,
    imports: [CommonModule, RouterModule, NavbarComponent],
    templateUrl: './catalogo-detalle.component.html',
    styleUrls: ['./catalogo-detalle.component.css']
})
export class CatalogoDetalleComponent implements OnInit {

    vehiculo: any = null;
    similares: any[] = [];   
    loading: boolean = true;
    loadingSimilares: boolean = false;
    error: string = '';

    constructor(
        private route: ActivatedRoute,
        private catalogoService: CatalogoService
    ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
        const id = params.get('id');

        if (id) {
            this.loading = true;
            this.cargarVehiculo(Number(id));
        } else {
            this.error = 'ID de vehículo no válido';
            this.loading = false;
        }
    });
}


    cargarVehiculo(id: number) {
        this.catalogoService.getVehiculoPorId(id.toString()).subscribe({
            next: (data) => {
                this.vehiculo = data;
                this.loading = false;

                // Cargar similares por marca
                this.cargarSimilaresPorMarca(data.marca, data.idVehiculo);
            },
            error: (err) => {
                console.error('Error cargando vehículo', err);
                this.error = 'No se pudo cargar la información del vehículo.';
                this.loading = false;
            }
        });
    }

    // =========================
    // SIMILARES POR MARCA
    // =========================
    cargarSimilaresPorMarca(marca: string, idActual: number) {
        if (!marca) return;

        this.loadingSimilares = true;

        this.catalogoService.getVehiculosPorMarca(marca).subscribe({
            next: (lista) => {

                // Excluir el vehículo actual
                this.similares = lista.filter(v => v.idVehiculo !== idActual);

                this.loadingSimilares = false;
            },
            error: (err) => { 
                console.error('Error trayendo similares', err);
                this.loadingSimilares = false;
            }
        });
    }
}
