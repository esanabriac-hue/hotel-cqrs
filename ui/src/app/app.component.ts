import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

interface BookingView {
  bookingId: string;
  guestName: string;
  roomNumber: string;
  status: string;
}

@Component({
  selector: 'app-root',
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  guestName = '';
  roomNumber = '';
  message = '';
  bookings: BookingView[] = [];

  private readonly commandApi = 'http://localhost:8081/api/commands/bookings';
  private readonly queryApi = 'http://localhost:8082/api/bookings';

  async createBooking(): Promise<void> {
    this.message = '';
    try {
      const response = await fetch(this.commandApi, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ guestName: this.guestName, roomNumber: this.roomNumber })
      });

      if (!response.ok) {
        this.message = `No se pudo enviar el comando (HTTP ${response.status})`;
        return;
      }

      const body = await response.json();
      this.message = `Comando aceptado. Booking ID: ${body.bookingId}`;
      this.guestName = '';
      this.roomNumber = '';
    } catch (error) {
      console.error(error);
      this.message = 'No se pudo enviar el comando (error de red)';
    }
  }

  async loadBookings(): Promise<void> {
    try {
      const response = await fetch(this.queryApi);
      if (!response.ok) {
        this.message = `No se pudo consultar el modelo de lectura (HTTP ${response.status})`;
        return;
      }

      this.bookings = await response.json();
      this.message = '';
    } catch (error) {
      console.error(error);
      this.message = 'No se pudo consultar el modelo de lectura (error de red)';
    }
  }
}
