export interface Reservation {
  id?: string;
  hotelId?: string;
  guestName: string;
  guestEmail: string;
  roomId: string;
  checkIn: string;
  checkOut: string;
}
