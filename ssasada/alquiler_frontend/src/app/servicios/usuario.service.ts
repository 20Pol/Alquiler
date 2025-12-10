import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UsuarioService {

    private apiUrl = 'http://localhost:8080/api/usuarios';

    constructor(private http: HttpClient) { }

    getUsuarios(): Observable<any[]> {
        return this.http.get<any[]>(this.apiUrl);
    }

    updateRol(id: number, rol: string): Observable<any> {
        return this.http.put<any>(`${this.apiUrl}/${id}/rol`, { rol });
    }

    deleteUsuario(id: number): Observable<any> {
        return this.http.delete<any>(`${this.apiUrl}/${id}`);
    }
}
