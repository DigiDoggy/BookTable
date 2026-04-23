package tablebook.backend.dto.response;

import tablebook.backend.entity.Reservation;
import tablebook.backend.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ReservationResponse(
        UUID id,
        UUID userId,
        UUID tableId,
        int peopleCount,
        LocalDate reservationDate,
        LocalTime reservationTime,
        LocalTime reservationEndTime,
        BookingStatus status,
        String preferences
) {

    public static ReservationResponse from(Reservation reservation) {
        UUID userId = null;
        if (reservation.getUser() != null) {
            userId = reservation.getUser().getId();
        }

        UUID tableId = null;
        if (reservation.getTable() != null) {
            tableId = reservation.getTable().getId();
        }

        return new ReservationResponse(
                reservation.getId(),
                userId,
                tableId,
                reservation.getPeopleCount(),
                reservation.getReservationDate(),
                reservation.getReservationTime(),
                reservation.getReservationEndTime(),
                reservation.getStatus(),
                reservation.getPreferences()
        );
    }
}

