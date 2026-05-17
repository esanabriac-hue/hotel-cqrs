package com.hotel.query.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hotel.query.service.BookingProjectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(QueryController.class)
class QueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingProjectionService bookingProjectionService;

    @Test
    void shouldReturnNotFoundWhenBookingDoesNotExist() throws Exception {
        given(bookingProjectionService.findById("missing")).willReturn(null);

        mockMvc.perform(get("/api/bookings/missing"))
                .andExpect(status().isNotFound());
    }
}
