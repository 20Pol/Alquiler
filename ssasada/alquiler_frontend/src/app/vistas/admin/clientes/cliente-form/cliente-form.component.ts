import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClienteService } from '../cliente.service';

@Component({
  selector: 'app-cliente-form',
  standalone: false,
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.css']
})
export class ClienteFormComponent implements OnInit {

  clienteForm!: FormGroup;
  id!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private clienteService: ClienteService
  ) {}

  ngOnInit(): void {
    this.clienteForm = this.fb.group({
      id: [''],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      dni: ['', [Validators.required, Validators.maxLength(8)]],
      telefono: [''],
      email: ['', Validators.email],
      direccion: ['']
    });

    // Cargar datos si es edición
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    if (this.id) {
      this.clienteService.obtenerPorId(this.id).subscribe(data => {
        this.clienteForm.patchValue(data);
      });
    }
  }

  guardarCliente() {
    if (this.clienteForm.invalid) return;

    const cliente = this.clienteForm.value;

    if (cliente.id) {
      // Actualizar
      this.clienteService.actualizar(cliente).subscribe(() => {
        this.router.navigate(['/clientes']);
      });
    } else {
      // Crear
      this.clienteService.crear(cliente).subscribe(() => {
        this.router.navigate(['/clientes']);
      });
    }
  }
}
