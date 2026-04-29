package tablebook.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;
import tablebook.backend.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@jakarta.persistence.Table(name = "reservations")
public class Reservation {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @NotNull
    @Column(name = "people_count", nullable = false)
    private int peopleCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    @NotNull
    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @NotNull
    @Column(name = "reservation_time", nullable = false)
    private LocalTime reservationTime;

    @Column(name = "reservation_end_time", nullable = false)
    private  LocalTime reservationEndTime;

    @Column(name = "reservation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column(name = "preferences", nullable = false)
    private String preferences;

    @PrePersist
    @PreUpdate
    private void updateReservationEndTime() {
        if(reservationTime == null){
            return ;
        }
        if(reservationTime.isBefore(LocalTime.of(17, 0))){
            reservationEndTime = reservationTime.plusHours(2);
        }else{
            reservationEndTime = reservationTime.plusHours(6);
        }
    }
}
