import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  private apiUrl = 'http://localhost:8080/api/reserva';

  constructor(private http: HttpClient) {}

  crearReserva(reserva: any) {
    return this.http.post(this.apiUrl, reserva);
  }
}
