package com.essaco.reservation_command_service;

import org.springframework.boot.SpringApplication;

public class TestReservationCommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ReservationCommandServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
