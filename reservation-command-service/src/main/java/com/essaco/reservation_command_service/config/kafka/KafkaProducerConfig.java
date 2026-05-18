package com.essaco.reservation_command_service.config.kafka;

import com.essaco.reservation_command_service.domain.event.ReservationCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ObjectMapper kafkaObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    @Primary
    public ProducerFactory<String, ReservationCreatedEvent> reservationEventProducerFactory(
            KafkaProperties kafkaProperties,
            ObjectMapper kafkaObjectMapper) {
        Map<String, Object> config = kafkaProperties.buildProducerProperties();
        JsonSerializer<ReservationCreatedEvent> serializer = new JsonSerializer<>(kafkaObjectMapper);
        serializer.setAddTypeInfo(false);
        return new DefaultKafkaProducerFactory<>(config, new StringSerializer(), serializer);
    }

    @Bean
    @Primary
    public KafkaTemplate<String, ReservationCreatedEvent> kafkaTemplate(
            ProducerFactory<String, ReservationCreatedEvent> reservationEventProducerFactory) {
        return new KafkaTemplate<>(reservationEventProducerFactory);
    }
}
