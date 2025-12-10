import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Catalogo } from '../clases/catalogo';

@Injectable({
  providedIn: 'root'
})
export class CatalogoService {

  private apiUrl = 'http://localhost:8080/api/vehiculos'; // Ajustar según el endpoint real

  constructor(private http: HttpClient) { }

  getVehiculos(): Observable<Catalogo[]> {
    return this.http.get<Catalogo[]>(this.apiUrl);
  }

  // Método adicional para obtener un vehículo por ID (ejemplo)
  getVehiculo(id: number): Observable<Catalogo> {
    return this.http.get<Catalogo>(`${this.apiUrl}/${id}`);
  }


  getVehiculosPorMarca(marca: string): Observable<Catalogo[]> {
    return this.http.get<Catalogo[]>(`${this.apiUrl}/marca/${marca}`);
  }

  getVehiculosPorAnio(anio: number): Observable<Catalogo[]> {
    return this.http.get<Catalogo[]>(`${this.apiUrl}/anio/${anio}`);
  }

  getVehiculosPorTipo(tipoVehiculo: string): Observable<Catalogo[]> {
    return this.http.get<Catalogo[]>(`${this.apiUrl}/tipo/${tipoVehiculo}`);

  }

  getVehiculoPorId(id: string) {
    return this.http.get<any>(`${this.apiUrl}/vehiculo/${id}`);
  }

  createVehiculo(vehiculo: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, vehiculo);
  }

  updateVehiculo(id: number, vehiculo: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, vehiculo);
  }

  deleteVehiculo(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }

  
}
