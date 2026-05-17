package com.essaco.reservation_command_service.domain.service;

import com.essaco.reservation_command_service.config.kafka.ReservationProducer;
import com.essaco.reservation_command_service.domain.entity.Reservation;
import com.essaco.reservation_command_service.domain.event.ReservationCreatedEvent;
import com.essaco.reservation_command_service.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCommandService {

    private final ReservationRepository repository;
    private final ReservationProducer producer;

    public Reservation createReservation(Reservation reservation) {
        Reservation saved = repository.save(reservation);

        ReservationCreatedEvent event = new ReservationCreatedEvent(
                saved.getId(),
                saved.getHotelId(),
                saved.getRoomId(),
                saved.getGuestName(),
                saved.getCheckIn(),
                saved.getCheckOut()
        );

        producer.publish(event);

        return saved;
    }
}

