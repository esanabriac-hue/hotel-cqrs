package com.essaco.reservation_query_service.config.kafka;

import com.essaco.reservation_query_service.domain.events.ReservationCreatedEvent;
import com.essaco.reservation_query_service.domain.model.ReservationView;
import com.essaco.reservation_query_service.domain.repository.ReservationViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationConsumer {

    private final ReservationViewRepository repository;

    @KafkaListener(
            topics = "${kafka.topic.reservation-events}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(ReservationCreatedEvent event) {
        log.info("Evento recibido de Kafka: reservationId={} roomId={}", event.getReservationId(), event.getRoomId());

        ReservationView view = new ReservationView(
                event.getReservationId(),
                event.getHotelId(),
                event.getRoomId(),
                event.getGuestName(),
                event.getCheckIn(),
                event.getCheckOut()
        );

        repository.save(view);
        log.info("Vista guardada en MongoDB: reservationId={}", event.getReservationId());
    }
}
