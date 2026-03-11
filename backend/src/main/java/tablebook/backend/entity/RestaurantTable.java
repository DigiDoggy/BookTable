package tablebook.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;
import tablebook.backend.enums.Zone;

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

    // Coordinates are not necessary for now. Will attach them later if needed.
    //todo We should do it this way because it will make it easier to find recommendations.
    // For example, if a table is occupied, we can look for a suitable nearby table.
    @Column(name = "x_position")
    private int xPosition;

    @Column(name = "y_position")
    private int yPosition;

    @Column(name = "near_window")
    private boolean isNearWindow;

    @Column(name = "near_play_area")
    private boolean isNearPlayArea;

    @Column(name = "privacy")
    private boolean isPrivacy;

    @Column(name = "occupied")
    private boolean isOccupied;

    @Column(name = "zone")
    @Enumerated(EnumType.STRING)
    private Zone zone;

   private int score;

}
