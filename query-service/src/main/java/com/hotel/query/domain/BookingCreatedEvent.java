package com.hotel.query.domain;

public record BookingCreatedEvent(
        String bookingId,
        String guestName,
        String roomNumber,
        String status
) {
}
