package com.essaco.reservation_query_service.domain.service;

import com.essaco.reservation_query_service.domain.events.ReservationCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService {

    private final JavaMailSender mailSender;

    public void sendConfirmation(ReservationCreatedEvent event) {
        String email = event.getGuestEmail();

        if (email == null || email.isBlank()) {
            log.warn("No se puede enviar confirmación: email vacío para reservationId={}", event.getReservationId());
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Confirmación de reserva #" + event.getReservationId());
            message.setText(buildEmailBody(event));

            mailSender.send(message);
            log.info("Correo de confirmación enviado a {} para reservationId={}", email, event.getReservationId());

        } catch (MailException e) {
            log.error("Error al enviar correo de confirmación a {} para reservationId={}", email, event.getReservationId(), e);
        }
    }

    private String buildEmailBody(ReservationCreatedEvent event) {
        return """
                Estimado/a %s,

                Su reserva ha sido confirmada exitosamente. A continuación los detalles:

                  ID de reserva : %s
                  Hotel         : %s
                  Habitación    : %s
                  Check-in      : %s
                  Check-out     : %s

                Gracias por elegirnos. ¡Le esperamos!

                Atentamente,
                El equipo del hotel
                """.formatted(
                event.getGuestName(),
                event.getReservationId(),
                event.getHotelId(),
                event.getRoomId(),
                event.getCheckIn(),
                event.getCheckOut()
        );
    }
}
