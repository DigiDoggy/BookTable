package tablebook.backend.enums;

import java.util.Map;
import java.util.Set;

public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    COMPLETED;

    // map of allowed transitions
    private static final Map<BookingStatus, Set<BookingStatus>> ALLOWED_TRANSITIONS = Map.of(
            PENDING, Set.of(CONFIRMED, CANCELLED),
            CONFIRMED, Set.of(COMPLETED, CANCELLED),
            COMPLETED, Set.of(),
            CANCELLED, Set.of()
    );

    //just to make sure that we don't have any mistakes'
    public boolean isAllowedTransition(BookingStatus nextStatus) {
        return ALLOWED_TRANSITIONS.get(this).contains(nextStatus);
    }
}
