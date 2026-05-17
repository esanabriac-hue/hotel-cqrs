import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class CommandReservationService {
  private baseUrl = 'http://localhost:8081/api/reservations';

  constructor(private http: HttpClient) {}

  createReservation(payload: any) {
    return this.http.post(`${this.baseUrl}`, payload);
  }
}
