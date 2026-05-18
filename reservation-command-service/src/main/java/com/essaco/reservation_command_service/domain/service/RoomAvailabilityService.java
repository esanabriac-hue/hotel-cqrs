package com.essaco.reservation_command_service.domain.service;

import com.essaco.reservation_command_service.domain.HotelInventory;
import com.essaco.reservation_command_service.domain.exception.InvalidReservationException;
import com.essaco.reservation_command_service.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomAvailabilityService {

    private final ReservationRepository repository;

    public List<String> findAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        validateDateRange(checkIn, checkOut);

        List<String> available = new ArrayList<>();
        for (int room = HotelInventory.MIN_ROOM; room <= HotelInventory.MAX_ROOM; room++) {
            String roomId = String.valueOf(room);
            boolean overlaps = repository.existsByRoomIdAndCheckInLessThanAndCheckOutGreaterThan(
                    roomId, checkOut, checkIn);
            if (!overlaps) {
                available.add(roomId);
            }
        }
        return available;
    }

    private void validateDateRange(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            throw new InvalidReservationException("Las fechas de entrada y salida son obligatorias");
        }
        if (!checkOut.isAfter(checkIn)) {
            throw new InvalidReservationException("La fecha de salida debe ser posterior a la de entrada");
        }
        if (checkIn.isBefore(LocalDate.now())) {
            throw new InvalidReservationException("La fecha de entrada no puede ser en el pasado");
        }
    }
}
