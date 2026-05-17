package com.essaco.reservation_command_service.domain.repository;

import com.essaco.reservation_command_service.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
}
