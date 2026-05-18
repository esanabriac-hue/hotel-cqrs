import { Component } from '@angular/core';
import { CommandService } from '../services/command-reservation';
import { FormsModule } from '@angular/forms';
import { Reservation } from '../model/reservation.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-create-reservation',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-reservation.component.html',
  styleUrls: ['./create-reservation.component.css'] // <-- Solo debes asegurar que esto esté aquí
})
export class CreateReservationComponent {
  readonly rooms = Array.from({ length: 10 }, (_, i) => String(i + 1));

  reservation: Reservation = {
    guestName: '',
    roomId: '1',
    checkIn: '',
    checkOut: ''
  };

  availableRooms: string[] = [];
  errorMessage = '';
  successMessage = '';

  constructor(private commandService: CommandService) {}

  onDatesChange(): void {
    this.errorMessage = '';
    this.successMessage = '';
    if (!this.reservation.checkIn || !this.reservation.checkOut) {
      this.availableRooms = [];
      return;
    }
    this.commandService
      .getAvailability(this.reservation.checkIn, this.reservation.checkOut)
      .subscribe({
        next: (rooms) => {
          this.availableRooms = rooms;
          if (this.reservation.roomId && !rooms.includes(this.reservation.roomId)) {
            this.errorMessage =
              'La habitación seleccionada no está disponible en esas fechas. Elija otra.';
          }
        },
        error: (err: HttpErrorResponse) => {
          this.availableRooms = [];
          this.errorMessage = CommandService.extractErrorMessage(err);
        }
      });
  }

  create(): void {
    this.errorMessage = '';
    this.successMessage = '';
    this.commandService.createReservation(this.reservation).subscribe({
      next: () => {
        this.successMessage = 'Reserva creada correctamente';
        this.reservation = {
          guestName: '',
          roomId: '1',
          checkIn: '',
          checkOut: ''
        };
        this.availableRooms = [];
      },
      error: (err: HttpErrorResponse) => {
        this.errorMessage = CommandService.extractErrorMessage(err);
      }
    });
  }
}
