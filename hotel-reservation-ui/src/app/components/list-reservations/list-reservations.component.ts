import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common'; // Necesario para el pipe de fecha
import { QueryService } from '../../services/query-reservation';
import { CommandService } from '../../services/command-reservation';
import { Reservation } from '../../model/reservation.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-list-reservations',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './list-reservations.component.html',
  styleUrls: ['./list-reservations.component.css'] // Asegúrate de tener este archivo
})
export class ListReservationsComponent implements OnInit {
  // Usamos Signals para una reactividad perfecta en la vista
  reservations = signal<Reservation[]>([]);
  errorMessage = signal<string>('');

  constructor(private queryService: QueryService) {}

  ngOnInit(): void {
    this.queryService.getReservations().subscribe({
      next: (data: any) => {
        // Validamos por si llega como texto crudo o dentro de otro objeto
        let parsedData = data;
        if (typeof data === 'string') {
          parsedData = JSON.parse(data);
        } else if (data && !Array.isArray(data) && data.data) {
          parsedData = data.data;
        }

        // Actualizamos el estado del Signal
        this.reservations.set(parsedData);
      },
      error: (err: HttpErrorResponse) => {
        this.errorMessage.set(CommandService.extractErrorMessage(err));
      }
    });
  }
}
