package com.hotel.command.domain;

public record BookingCreatedEvent(
        String bookingId,
        String guestName,
        String roomNumber,
        String status
) {
}
