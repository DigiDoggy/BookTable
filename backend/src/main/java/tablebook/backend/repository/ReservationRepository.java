package tablebook.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tablebook.backend.entity.Reservation;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID>{
    
}
