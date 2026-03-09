package tablebook.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tablebook.backend.entity.RestaurantTable;

import java.util.UUID;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, UUID> {
    RestaurantTable findByTableNumber(int tableNumber);
    RestaurantTable findByTableId(UUID tableId);
    RestaurantTable findByUserId(UUID userId);
}
