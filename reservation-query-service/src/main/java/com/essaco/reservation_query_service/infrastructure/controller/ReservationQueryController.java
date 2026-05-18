package com.essaco.reservation_query_service.infrastructure.controller;

import com.essaco.reservation_query_service.domain.model.ReservationView;
import com.essaco.reservation_query_service.domain.service.ReservationQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*") // para que Angular (4200) pueda llamar sin problema
public class ReservationQueryController {

    private final ReservationQueryService reservationQueryService;

    public ReservationQueryController(ReservationQueryService reservationQueryService) {
        this.reservationQueryService = reservationQueryService;
    }

    @GetMapping
    public List<ReservationView> getAll() {
        return reservationQueryService.findAll();
    }
}

