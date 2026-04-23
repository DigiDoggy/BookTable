package tablebook.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tablebook.backend.dto.request.CreateReservationRequest;
import tablebook.backend.entity.Reservation;
import tablebook.backend.entity.BookingTable;
import tablebook.backend.entity.User;
import tablebook.backend.enums.BookingStatus;
import tablebook.backend.exceptions.CrmErrorMessage;
import tablebook.backend.exceptions.CrmException;
import tablebook.backend.repository.ReservationRepository;
import tablebook.backend.repository.RestaurantTableRepository;
import tablebook.backend.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RestaurantTableRepository tableRepository;

    //post request
    public Reservation createReservation(CreateReservationRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new CrmException(CrmErrorMessage.USER_NOT_FOUND));

        BookingTable table = tableRepository.findById(request.tableId())
                .orElseThrow(() -> new CrmException(CrmErrorMessage.TABLE_NOT_FOUND));

        if (request.peopleCount() > table.getCapacity()) {
           throw new CrmException(CrmErrorMessage.PEOPLE_COUNT_EXCEEDS_CAPACITY);
        }

        LocalTime startTime = request.time();
        LocalTime endTime = calculateEndTime(startTime);

        List<Reservation> reservationsForTable = reservationRepository
                .findByTableIdAndReservationDate(table.getId(), request.date());

        for (Reservation existingReservation : reservationsForTable) {
            if (isOverlapping(startTime, endTime,
                    existingReservation.getReservationTime(),
                    existingReservation.getReservationEndTime())) {
                throw new CrmException(CrmErrorMessage.TABLE_NOT_AVAILABLE);
            }
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setTable(table);
        reservation.setPeopleCount(request.peopleCount());
        reservation.setReservationDate(request.date());
        reservation.setReservationTime(startTime);
        reservation.setReservationEndTime(endTime);
        reservation.setStatus(BookingStatus.CONFIRMED);
        reservation.setPreferences(request.preferences());

        return reservationRepository.save(reservation);
    }

    //get request
    public Reservation getReservationById(UUID id) {
        return reservationRepository.findById(id)
                .orElseThrow( () -> new CrmException(CrmErrorMessage.RESERVATION_NOT_FOUND));
    }

    //get all Reservations for calendar
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }


    public List<Reservation> getReservationsForDate(LocalDate date) {
        return reservationRepository.findByReservationDate(date);
    }

    public List<Reservation> getReservationsForTableAndDate(UUID tableId, LocalDate date) {
        return reservationRepository.findByTableIdAndReservationDate(tableId, date);
    }

    private LocalTime calculateEndTime(LocalTime startTime) {
        if (startTime == null) {
            return null;
        }

        if (startTime.isBefore(LocalTime.of(17, 0))) {
            return startTime.plusHours(2);
        } else {
            return startTime.plusHours(6);
        }
    }

    private boolean isOverlapping(LocalTime start1,
                                  LocalTime end1,
                                  LocalTime start2,
                                  LocalTime end2) {
        if (start1 == null || end1 == null || start2 == null || end2 == null) {
            return false;
        }
        return !start1.isAfter(end2) && !end1.isBefore(start2);
    }

}
