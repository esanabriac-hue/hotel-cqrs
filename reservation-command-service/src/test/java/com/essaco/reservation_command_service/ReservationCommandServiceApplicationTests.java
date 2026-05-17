package com.essaco.reservation_command_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ReservationCommandServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
