package tablebook.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tablebook.backend.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
