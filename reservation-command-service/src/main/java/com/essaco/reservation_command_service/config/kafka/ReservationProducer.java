package com.essaco.reservation_command_service.config.kafka;

import com.essaco.reservation_command_service.domain.event.ReservationCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationProducer {

    private final KafkaTemplate<String, ReservationCreatedEvent> kafkaTemplate;

    @Value("${kafka.topic.reservation-events}")
    private String topic;

    public void publish(ReservationCreatedEvent event) {
        try {
            kafkaTemplate.send(topic, event.getReservationId(), event)
                    .get(10, TimeUnit.SECONDS);
            log.info("Evento publicado en Kafka topic={} reservationId={}", topic, event.getReservationId());
        } catch (Exception e) {
            log.error("Error publicando evento en Kafka topic={} reservationId={}", topic, event.getReservationId(), e);
            throw new IllegalStateException("No se pudo publicar el evento de reserva en Kafka", e);
        }
    }
}
