package tablebook.backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;
import tablebook.backend.enums.UserRole;

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
    @Column(name = "user_name")
    private String userName;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Column(name = "like_zone")
    private List<String> zones;

    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "phone", unique = true)
    private String phone;


    //TODO (orders some list...) for AI: The system analyzes customer preferences based on their orders and generates personalized recommendations.
    // When the menu is updated, each customer receives individual offers with dishes they are highly likely to enjoy.

    private String orders;
}




