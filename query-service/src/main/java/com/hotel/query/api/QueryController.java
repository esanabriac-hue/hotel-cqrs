package com.hotel.query.api;

import com.hotel.query.domain.BookingView;
import com.hotel.query.service.BookingProjectionService;
import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/bookings")
public class QueryController {

    private final BookingProjectionService bookingProjectionService;

    public QueryController(BookingProjectionService bookingProjectionService) {
        this.bookingProjectionService = bookingProjectionService;
    }

    @GetMapping
    public Collection<BookingView> listBookings() {
        return bookingProjectionService.findAll();
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingView> getBooking(@PathVariable String bookingId) {
        BookingView booking = bookingProjectionService.findById(bookingId);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(booking);
    }
}
