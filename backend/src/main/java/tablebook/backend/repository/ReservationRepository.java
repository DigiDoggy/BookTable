package tablebook.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tablebook.backend.entity.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID>{

    List<Reservation> findByReservationDate(LocalDate reservationDate);

    List<Reservation> findByTableIdAndReservationDate(UUID tableId, LocalDate reservationDate);
}
