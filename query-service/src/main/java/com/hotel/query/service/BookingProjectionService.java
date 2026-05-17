package com.hotel.query.service;

import com.hotel.query.domain.BookingCreatedEvent;
import com.hotel.query.domain.BookingView;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingProjectionService {

    private final Map<String, BookingView> bookings = new ConcurrentHashMap<>();

    @KafkaListener(topics = "hotel-booking-events", groupId = "query-service")
    public void onBookingCreated(BookingCreatedEvent event) {
        bookings.put(event.bookingId(), new BookingView(
                event.bookingId(),
                event.guestName(),
                event.roomNumber(),
                event.status()
        ));
    }

    public BookingView findById(String bookingId) {
        return bookings.get(bookingId);
    }

    public Collection<BookingView> findAll() {
        return bookings.values();
    }
}
