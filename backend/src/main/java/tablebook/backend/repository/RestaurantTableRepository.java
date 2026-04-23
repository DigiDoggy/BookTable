package tablebook.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tablebook.backend.entity.BookingTable;

import java.util.UUID;


public interface RestaurantTableRepository extends JpaRepository<BookingTable, UUID> {
    BookingTable findByTableNumber(int tableNumber);
}
