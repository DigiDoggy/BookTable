package tablebook.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tablebook.backend.dto.RestaurantTableDTO;
import tablebook.backend.dto.request.ClientRecommendationRequest;
import tablebook.backend.dto.response.RestaurantTableResponse;
import tablebook.backend.entity.BookingTable;
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

        List<BookingTable> tables = tableRepository.findAll();

        List<BookingTable> freeTables = getFreeTables(request.time(), request.date(), tables);
        // creating recommendation score for request
        calculateRecommendationScore(freeTables, request);

        List<BookingTable> recommendationTables = getRecommendation(request.zone(), request.numberOfPeople(), freeTables);
        List<BookingTable> occupiedTables = getOccupiedTables(tables);

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
    private List<BookingTable> getFreeTables(LocalTime time, LocalDate date, List<BookingTable> tables) {

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



        for (BookingTable table : tables) {
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
    private List<BookingTable> getRecommendation(Zone zone,
                                                 int peopleCount,
                                                 List<BookingTable> tables) {


        return tables.stream()
                .filter(table -> table.getZone().equals(zone))
                .filter(table -> table.getCapacity() >= peopleCount)
                .sorted(Comparator.comparingInt(BookingTable::getScore).reversed()
                        .thenComparing(BookingTable::getCapacity))
                .limit(3)
                .toList();
    }

    //get occupied tables
    private List<BookingTable> getOccupiedTables(List<BookingTable> tables) {
        return tables.stream()
                .filter(BookingTable::isOccupied)
                .toList();
    }

    //can implement if create logic of coordinate
    private int calculateRecommendationScore(List<BookingTable> tables, ClientRecommendationRequest request) {
      for (BookingTable table : tables) {
          int score = 0;

          //cheking zone
          if (request.zone() !=null && table.getZone().equals(request.zone())) {
              score+=30;
          }

          // checking people count
          int difference = request.numberOfPeople() - table.getCapacity();
          if (difference <= 0) {
              score+=10;
              // - difference, like fine
              score-=difference;
          }else{
              //  If there are no places,
              //  then we remove it from the recommendations by rating
              score-=100;
          }

          if (request.isNearWindow() && table.isNearWindow()) {
              score += 10;
          }
          if (request.isNearPlayArea() && table.isNearPlayArea()) {
              score += 10;
          }
          if (request.isPrivacy() && table.isPrivacy()) {
              score += 10;
          }

          table.setScore(score);
      }



        return 0;


    }
}