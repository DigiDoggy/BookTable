package tablebook.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tablebook.backend.entity.Reservation;
import tablebook.backend.entity.RestaurantTable;
import tablebook.backend.repository.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public List<RestaurantTable> getRandomSeats() {
    }
}
