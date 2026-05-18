package com.essaco.reservation_command_service.domain.repository;

import com.essaco.reservation_command_service.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    boolean existsByRoomIdAndCheckInLessThanAndCheckOutGreaterThan(
            String roomId, LocalDate checkOut, LocalDate checkIn);
}
