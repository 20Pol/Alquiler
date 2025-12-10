import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  private apiUrl = 'http://localhost:8080/api/perfil'; 
  private reservaUrl = 'http://localhost:8080/api/reserva';
  private pagoUrl = 'http://localhost:8080/api/pago';

  constructor(private http: HttpClient) {}

  // Obtener datos del cliente por ID
  getClientePorId(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  // Obtener historial de reservas del cliente
  getReservasCliente(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/reservas/${id}`);
  }

  // Obtener historial de pagos del cliente
  getPagosCliente(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/pagos/${id}`);
  }

  // Cancelar reserva
  cancelarReserva(idReserva: number): Observable<void> {
    return this.http.delete<void>(`${this.reservaUrl}/${idReserva}`);
  }

  // Procesar pago
  procesarPago(pagoData: any): Observable<any> {
    return this.http.post<any>(this.pagoUrl, pagoData);
  }
}

