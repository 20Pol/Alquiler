import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { VehiculoListComponent } from './vehiculo-list/vehiculo-list.component';
import { VehiculoDetalleComponent } from './vehiculo-detalle/vehiculo-detalle.component';
import { VehiculoFormComponent } from './vehiculo-form/vehiculo-form.component';

const routes: Routes = [
  { path: '', component: VehiculoListComponent },
  { path: 'detalle/:id', component: VehiculoDetalleComponent },
  { path: 'nuevo', component: VehiculoFormComponent },
  { path: 'editar/:id', component: VehiculoFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VehiculoRoutingModule {}
