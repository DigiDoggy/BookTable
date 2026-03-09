package tablebook.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tablebook.backend.entity.Reservation;

import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID>{
    Optional<Reservation> findByReservationId(UUID reservationId);
    Optional<Reservation> findByUserId(UUID userId);
    Optional<Reservation> findByTableId(UUID tableId);
}
