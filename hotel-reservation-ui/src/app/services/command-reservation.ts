import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Reservation } from '../model/reservation.model';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommandService {

  private apiUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}

  createReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(`${this.apiUrl}/reservations`, reservation);
  }

  getAvailability(checkIn: string, checkOut: string): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/rooms/availability`, {
      params: { checkIn, checkOut }
    });
  }

  static extractErrorMessage(error: HttpErrorResponse): string {
    if (error.error?.message) {
      return error.error.message;
    }
    return 'Error al procesar la solicitud';
  }
}
