package com.essaco.reservation_query_service.config.kafka;

import com.essaco.reservation_query_service.domain.events.ReservationCreatedEvent;
import com.essaco.reservation_query_service.domain.model.ReservationView;
import com.essaco.reservation_query_service.domain.repository.ReservationViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationConsumer {

    private final ReservationViewRepository repository;

    @KafkaListener(topics = "${kafka.topic.reservation-events}", groupId = "reservation-query")
    public void consume(ReservationCreatedEvent event) {

        ReservationView view = new ReservationView(
                event.getReservationId(),
                event.getHotelId(),
                event.getRoomId(),
                event.getGuestName(),
                event.getCheckIn(),
                event.getCheckOut()
        );

        repository.save(view);
    }
}

