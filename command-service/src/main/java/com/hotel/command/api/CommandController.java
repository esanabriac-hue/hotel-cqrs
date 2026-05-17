package com.hotel.command.api;

import com.hotel.command.config.KafkaTopicConfig;
import com.hotel.command.domain.BookingCreatedEvent;
import com.hotel.command.domain.CreateBookingCommand;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/commands/bookings")
public class CommandController {

    private final KafkaTemplate<String, BookingCreatedEvent> kafkaTemplate;

    public CommandController(KafkaTemplate<String, BookingCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<BookingAcceptedResponse> createBooking(@Valid @RequestBody CreateBookingCommand command) {
        String bookingId = command.bookingId() == null || command.bookingId().isBlank()
                ? UUID.randomUUID().toString()
                : command.bookingId();

        BookingCreatedEvent event = new BookingCreatedEvent(
                bookingId,
                command.guestName(),
                command.roomNumber(),
                "CREATED"
        );

        kafkaTemplate.send(KafkaTopicConfig.BOOKING_EVENTS_TOPIC, bookingId, event);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookingAcceptedResponse(bookingId, "CREATED"));
    }

    public record BookingAcceptedResponse(String bookingId, String status) {
    }
}
