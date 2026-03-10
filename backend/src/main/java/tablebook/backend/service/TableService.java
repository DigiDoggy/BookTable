package tablebook.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tablebook.backend.entity.RestaurantTable;
import tablebook.backend.repository.RestaurantTableRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TableService {
    private final RestaurantTableRepository tableRepository;

    //todo need add tables to DB

    // that coming when user wrote date and time
    public List<RestaurantTable> getTables() {
        List<RestaurantTable> tables = tableRepository.findAll();

        if (tables.isEmpty()) {
            throw new RuntimeException("No tables found");
        }

        Random random = new Random();

        for (RestaurantTable table : tables) {
            if (random.nextDouble() < 0.3) {
                table.setAccessible(false);
            }
        }
        // saving tables to DB
        tableRepository.saveAll(tables);

        return tables;
    }
}