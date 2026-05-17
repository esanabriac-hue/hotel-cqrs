import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class QueryReservationService {
  private baseUrl = 'http://localhost:8082/api/reservations';

  constructor(private http: HttpClient) {}

  getReservations() {
    return this.http.get(this.baseUrl);
  }
}

