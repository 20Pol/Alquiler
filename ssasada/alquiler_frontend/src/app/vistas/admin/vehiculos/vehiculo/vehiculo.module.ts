import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { VehiculoRoutingModule } from './vehiculo-routing.module';

import { VehiculoListComponent } from './vehiculo-list/vehiculo-list.component';
import { VehiculoDetalleComponent } from './vehiculo-detalle/vehiculo-detalle.component';
import { VehiculoFormComponent } from './vehiculo-form/vehiculo-form.component';

@NgModule({
  declarations: [
    VehiculoListComponent,
    VehiculoDetalleComponent,
    VehiculoFormComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    VehiculoRoutingModule
  ]
})
export class VehiculoModule {}
