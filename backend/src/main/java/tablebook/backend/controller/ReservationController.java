package tablebook.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tablebook.backend.dto.request.CreateReservationRequest;
import tablebook.backend.dto.response.ReservationResponse;
import tablebook.backend.entity.Reservation;
import tablebook.backend.service.ReservationService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    //create reservation
    @PostMapping()
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody CreateReservationRequest request) {
        Reservation reservation = reservationService.createReservation(request);

        URI location = URI.create("/reservations/" + reservation.getId());

        //response with link to reservation
        return ResponseEntity.created(location)
                .body(ReservationResponse.from(reservation));
    }

    //get reservation by id
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable UUID id) {
        Reservation reservation = reservationService.getReservationById(id);

        return ResponseEntity.ok(ReservationResponse.from(reservation));

    }

    // geet all reservation date. Client can choose tableId to get reservations for a specific table
    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getReservations(@RequestParam LocalDate date,
                                                                     @RequestParam(required = false) UUID tableId) {
        List<Reservation> reservations;
        if (tableId != null) {
            reservations = reservationService.getReservationsForTableAndDate(tableId, date);
        } else {
            reservations = reservationService.getReservationsForDate(date);
        }


        List<ReservationResponse> response = reservations.stream()
                .map(ReservationResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }
}
