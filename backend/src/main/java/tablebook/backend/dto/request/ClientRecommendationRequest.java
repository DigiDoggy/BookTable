package tablebook.backend.dto.request;

import tablebook.backend.enums.Zone;

import java.time.LocalDate;
import java.time.LocalTime;

public record ClientRecommendationRequest(
        LocalDate date,
        LocalTime time,
        Zone zone,
        boolean isNearPlayArea,
        boolean isNearWindow,
        boolean isPrivacy,
        int numberOfPeople
) {
}
