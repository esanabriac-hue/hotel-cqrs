package com.essaco.reservation_command_service.domain.service;

import com.essaco.reservation_command_service.domain.HotelInventory;
import com.essaco.reservation_command_service.domain.entity.Reservation;
import com.essaco.reservation_command_service.domain.exception.InvalidReservationException;
import com.essaco.reservation_command_service.domain.exception.RoomNotAvailableException;
import com.essaco.reservation_command_service.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ReservationValidator {

    private final ReservationRepository repository;

    public void validateForCreate(Reservation reservation) {
        if (reservation.getGuestName() == null || reservation.getGuestName().isBlank()) {
            throw new InvalidReservationException("El nombre del huésped es obligatorio");
        }
        if (reservation.getRoomId() == null || reservation.getRoomId().isBlank()) {
            throw new InvalidReservationException("La habitación es obligatoria");
        }
        if (reservation.getCheckIn() == null || reservation.getCheckOut() == null) {
            throw new InvalidReservationException("Las fechas de entrada y salida son obligatorias");
        }

        int roomNumber;
        try {
            roomNumber = Integer.parseInt(reservation.getRoomId().trim());
        } catch (NumberFormatException e) {
            throw new InvalidReservationException(
                    "La habitación debe ser un número entre " + HotelInventory.MIN_ROOM + " y " + HotelInventory.MAX_ROOM);
        }

        if (roomNumber < HotelInventory.MIN_ROOM || roomNumber > HotelInventory.MAX_ROOM) {
            throw new InvalidReservationException(
                    "Solo hay habitaciones del " + HotelInventory.MIN_ROOM + " al " + HotelInventory.MAX_ROOM);
        }

        reservation.setRoomId(String.valueOf(roomNumber));

        if (!reservation.getCheckOut().isAfter(reservation.getCheckIn())) {
            throw new InvalidReservationException("La fecha de salida debe ser posterior a la de entrada");
        }

        if (reservation.getCheckIn().isBefore(LocalDate.now())) {
            throw new InvalidReservationException("La fecha de entrada no puede ser en el pasado");
        }

        boolean overlaps = repository.existsByRoomIdAndCheckInLessThanAndCheckOutGreaterThan(
                reservation.getRoomId(),
                reservation.getCheckOut(),
                reservation.getCheckIn()
        );

        if (overlaps) {
            throw new RoomNotAvailableException(
                    "La habitación " + reservation.getRoomId() + " no está disponible en las fechas seleccionadas");
        }
    }
}
