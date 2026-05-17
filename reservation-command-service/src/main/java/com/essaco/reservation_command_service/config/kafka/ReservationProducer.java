package com.essaco.reservation_command_service.config.kafka;

import com.essaco.reservation_command_service.domain.event.ReservationCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.reservation-events}")
    private String topic;

    public void publish(ReservationCreatedEvent event) {
        kafkaTemplate.send(topic, event.getReservationId(), event);
    }
}

