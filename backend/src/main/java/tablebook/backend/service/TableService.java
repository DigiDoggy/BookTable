package tablebook.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tablebook.backend.dto.RestaurantTableDTO;
import tablebook.backend.dto.request.ClientRecommendationRequest;
import tablebook.backend.dto.response.RestaurantTableResponse;
import tablebook.backend.entity.RestaurantTable;
import tablebook.backend.enums.Zone;
import tablebook.backend.repository.RestaurantTableRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TableService {
    // generate occupied tables only once
    private boolean initialized = false;

    private final RestaurantTableRepository tableRepository;

    // that coming when user wrote date and time
    public RestaurantTableResponse getTables(ClientRecommendationRequest request) {

        List<RestaurantTable> tables = tableRepository.findAll();

        List<RestaurantTable> freeTables = getFreeTables(request.time(), request.date(), tables);
        List<RestaurantTable> recommendationTables = getRecommendation(request.zone(), request.numberOfPeople(), freeTables);
        List<RestaurantTable> occupiedTables = getOccupiedTables(tables);

        //mapping to dto

        List<RestaurantTableDTO> freeTablesDTO = freeTables.stream()
                .map(RestaurantTableDTO::toDTO).
                toList();
        List<RestaurantTableDTO> recommendationTablesDTO = recommendationTables.stream()
                .map(RestaurantTableDTO::toDTO)
                .toList();
        List<RestaurantTableDTO> occupiedTablesDTO = occupiedTables.stream()
                .map(RestaurantTableDTO::toDTO)
                .toList();

        return new RestaurantTableResponse(
                freeTablesDTO,
                recommendationTablesDTO,
                occupiedTablesDTO
        );
    }


    // I'm just improvising with my workload here.
    //It depends on the booking time and date.
    private List<RestaurantTable> getFreeTables(LocalTime time, LocalDate date, List<RestaurantTable> tables) {

        if (initialized) {
            return tables.stream()
                    .filter(table -> !table.isOccupied())
                    .toList();
        }

        float loadIndex;

        Random random = new Random();


        if (tables.isEmpty()) {
            throw new RuntimeException("No tables found");
        }

        // Simulate occupancy using a load index.

        if (time.isBefore(LocalTime.of(12, 0))) {
            loadIndex = 0.3f;
        } else if (time.isBefore(LocalTime.of(17, 0))) {
            loadIndex = 0.5f;
        } else {
            loadIndex = 0.7f;
        }



        for (RestaurantTable table : tables) {
            if (random.nextDouble() < loadIndex) {
                table.setOccupied(true);
            }

            //Removes reservations for tomorrow
            if (date.isAfter(LocalDate.now())) {
                table.setOccupied(false);
            }
            tableRepository.save(table);
        }

        // toggle initialized to true
        initialized = true;
        return tables.stream()
                .filter(table -> !table.isOccupied())
                .toList();
    }

    //get recommendation tables
    private List<RestaurantTable> getRecommendation(Zone zone,
                                                    int peopleCount,
                                                    List<RestaurantTable> tables) {

        return tables.stream()
                .filter(table -> table.getZone().equals(zone))
                .filter(table -> table.getCapacity() >= peopleCount)
                .sorted(Comparator.comparingInt(RestaurantTable::getCapacity))
                .limit(3)
                .toList();
    }

    //get occupied tables
    private List<RestaurantTable> getOccupiedTables(List<RestaurantTable> tables) {
        return tables.stream()
                .filter(RestaurantTable::isOccupied)
                .toList();
    }

    //can implement if create logic of coordinate
    private int calculateRecommendationScore(List<RestaurantTable> tables, ClientRecommendationRequest request) {
        return 0;
    }
}