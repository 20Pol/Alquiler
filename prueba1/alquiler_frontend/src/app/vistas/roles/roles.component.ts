import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../../servicios/usuario.service';

@Component({
  selector: 'app-roles',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './roles.component.html',
  styleUrl: './roles.component.css'
})
export class RolesComponent implements OnInit {
  listaUsuarios: any[] = [];
  listaRoles = ['ADMIN', 'EMPLEADO', 'CLIENTE'];

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios() {
    this.usuarioService.getUsuarios().subscribe({
      next: (data) => {
        this.listaUsuarios = data;
        // Inicializar nuevoRol con el rol actual
        this.listaUsuarios.forEach(u => u.nuevoRol = u.rol?.nombreRol || 'CLIENTE');
      },
      error: (err) => console.error('Error cargando usuarios', err)
    });
  }

  asignarRol(usuario: any) {
    if (confirm(`¿Seguro de cambiar el rol de ${usuario.username} a ${usuario.nuevoRol}?`)) {
      this.usuarioService.updateRol(usuario.idUsuario, usuario.nuevoRol).subscribe({
        next: (res) => {
          alert('Rol actualizado correctamente');
          this.cargarUsuarios();
        },
        error: (err) => {
          console.error('Error actualizando rol', err);
          alert('Error al actualizar rol');
        }
      });
    }
  }

  eliminarUsuario(usuario: any) {
    if (confirm(`¿Estás seguro de eliminar al usuario ${usuario.username}? Esta acción no se puede deshacer.`)) {
      this.usuarioService.deleteUsuario(usuario.idUsuario).subscribe({
        next: (res: any) => {
          alert('Usuario eliminado correctamente');
          this.cargarUsuarios();
        },
        error: (err: any) => {
          console.error('Error eliminando usuario', err);
          alert('Error al eliminar usuario');
        }
      });
    }
  }
}
