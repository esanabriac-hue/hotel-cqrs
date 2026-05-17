package com.hotel.query.domain;

public record BookingView(
        String bookingId,
        String guestName,
        String roomNumber,
        String status
) {
}
