package tablebook.backend.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateReservationRequest(
        UUID userId,
        UUID tableId,
        LocalDate date,
        LocalTime time,
        int peopleCount,
        String preferences
) {
}

