package tablebook.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tablebook.backend.entity.Reservation;
import tablebook.backend.service.ReservationService;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    // we take random seats when reload page;
    @GetMapping("/tables")
    public ResponseEntity<Reservation> getTables() {
        return ResponseEntity.ok(reservationService.getRandomSeats());
    }
}
