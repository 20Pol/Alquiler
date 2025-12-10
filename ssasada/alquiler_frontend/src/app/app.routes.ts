
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './vistas/inicio/inicio.component';
import { CatalogoComponent } from './vistas/catalogo/catalogo.component';
import { PerfilComponent } from './vistas/perfil/perfil.component';
import { ReservaComponent } from './vistas/reserva-form/reserva-form.component';
import { DevolucionesComponent } from './vistas/devoluciones/devoluciones.component';
import { DashboardLayoutComponent } from './vistas/dashboard-layout/dashboard-layout.component';
import { NuevoAlquilerComponent } from './vistas/nuevo-alquiler/nuevo-alquiler.component';
import { ListaAlquileresComponent } from './vistas/lista-alquileres/lista-alquileres.component';
import { DashboardProgramComponent } from './vistas/dashboard-program/dashboard-program.component';
import { GestionPagosComponent } from './vistas/gestion-pagos/gestion-pagos.component';
import { LoginComponent } from './vistas/login/login.component';
import { RegistroComponent } from './vistas/registro/registro.component';
import { VehiculoDetalleComponent } from './vistas/admin/vehiculos/vehiculo-detalle/vehiculo-detalle.component';
import { VehiculoFormComponent } from './vistas/admin/vehiculos/vehiculo-form/vehiculo-form.component';
import { VehiculoListarComponent } from './vistas/admin/vehiculos/vehiculo-listar/vehiculo-listar.component';
import { CatalogoDetalleComponent } from './vistas/catalogo-detalle/catalogo-detalle.component';
import { RolesComponent } from './vistas/roles/roles.component';
import { RoleGuard } from './guards/role.guard';
import { DashComponent } from './vistas/dash/dash.component';


export const routes: Routes = [

  { path: '', component: InicioComponent },
  { path: 'catalogo', component: CatalogoComponent },
  { path: 'perfil', component: PerfilComponent },
  { path: 'reserva/:id', component: ReservaComponent },
  { path: 'login', component: LoginComponent },
  { path: 'catalogo/:id', component: CatalogoDetalleComponent },


  // 🛠️ Dashboard (protegido)
  {
    path: 'dashboard',
    component: DashboardLayoutComponent,
    canActivate: [RoleGuard],
    data: { roles: ['ADMIN', 'EMPLEADO'] },
    children: [
      { path: '', component: DashboardProgramComponent }, // 👈 Ruta raíz
      { path: 'vehiculos', component: DashComponent }, // 👈 Nueva ruta
      { path: 'roles', component: RolesComponent },
      { path: 'devoluciones', component: DevolucionesComponent },
      { path: 'nuevo-alquiler', component: NuevoAlquilerComponent },
      { path: 'lista-alquileres', component: ListaAlquileresComponent },
      { path: 'gestion-pagos', component: GestionPagosComponent }

    ]
  },
  { path: 'registro', component: RegistroComponent },

  // Vehículos
  { path: 'vehiculos/listar', component: VehiculoListarComponent },
  { path: 'vehiculos/detalle/:id', component: VehiculoDetalleComponent },
  { path: 'vehiculos/nuevo', component: VehiculoFormComponent },
  { path: 'vehiculos/editar/:id', component: VehiculoFormComponent },


  { path: '**', redirectTo: '' }
];

@NgModule({

  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

