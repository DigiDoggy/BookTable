package tablebook.backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;
import tablebook.backend.enums.UserRole;
import tablebook.backend.enums.Zone;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @UuidGenerator
    private UUID id;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "zone", nullable = false)
    @Enumerated(EnumType.STRING)
    private Zone Zone;

    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "phone", unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;
}




