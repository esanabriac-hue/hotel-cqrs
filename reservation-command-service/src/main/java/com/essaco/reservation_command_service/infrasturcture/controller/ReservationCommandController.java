package com.essaco.reservation_command_service.infrasturcture.controller;

import com.essaco.reservation_command_service.domain.entity.Reservation;
import com.essaco.reservation_command_service.domain.service.ReservationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationCommandController {

    private final ReservationCommandService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(service.createReservation(reservation));
    }
}

