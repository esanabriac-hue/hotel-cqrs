package com.hotel.command.domain;

import jakarta.validation.constraints.NotBlank;

public record CreateBookingCommand(
        String bookingId,
        @NotBlank String guestName,
        @NotBlank String roomNumber
) {
}
