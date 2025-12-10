import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Reserva } from '../clases/reserva';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  private apiUrl = 'http://localhost:8080/api/reserva';

  constructor(private http: HttpClient) {}

  crearReserva(reserva: any) {
    return this.http.post(this.apiUrl, reserva);
  }

  // Obtener todas las reservas
  getReservas(): Observable<Reserva[]> {
  return this.http.get<Reserva[]>(this.apiUrl);
}

  // Obtener una reserva por ID
  getReservaById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  actualizarReserva(id: number, estado: string): Observable<any> {
  return this.http.put<any>(`${this.apiUrl}/${id}/estado?estado=${estado}`, {});
}


  // Eliminar reserva
  eliminarReserva(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }


 
}
