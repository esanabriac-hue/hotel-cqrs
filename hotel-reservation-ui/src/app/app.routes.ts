import { Routes } from '@angular/router';
import { CreateReservationComponent } from './components/create-reservation.component';
import { ListReservationsComponent } from './components/list-reservations/list-reservations.component';

export const routes: Routes = [
  { path: 'create', component: CreateReservationComponent },
  { path: 'list', component: ListReservationsComponent },
  { path: '**', redirectTo: 'list' }
];
