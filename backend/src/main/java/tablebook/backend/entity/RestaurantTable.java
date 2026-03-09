package tablebook.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@jakarta.persistence.Table(name = "tables")
public class RestaurantTable {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "table_number")
    @NotNull
    @Size(min = 1, max = 10)
    private String tableNumber;

    @OneToMany(mappedBy = "tables", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();

    @NotNull
    @Column(name = "capacity")
    private int capacity;

    @NotNull
    @Column(name = "x_position")
    private int xPosition;

    @NotNull
    @Column(name = "y_position")
    private int yPosition;

    @Column(name = "near_window")
    private boolean nearWindow;

    @Column(name = "near_play_area")
    private boolean nearPlayArea;

    @Column(name = "accessible")
    private boolean accessible;

    @Column(name = "privacy")
    private boolean privacy;
}
