package com.hotel.query.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.hotel.query.domain.BookingCreatedEvent;
import org.junit.jupiter.api.Test;

class BookingProjectionServiceTest {

    @Test
    void shouldProjectBookingEventIntoReadModel() {
        BookingProjectionService service = new BookingProjectionService();

        service.onBookingCreated(new BookingCreatedEvent("b-1", "Ana", "101", "CREATED"));

        assertEquals("Ana", service.findById("b-1").guestName());
        assertEquals(1, service.findAll().size());
    }
}
