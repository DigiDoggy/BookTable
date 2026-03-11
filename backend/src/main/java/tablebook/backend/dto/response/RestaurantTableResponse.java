package tablebook.backend.dto.response;

import tablebook.backend.dto.RestaurantTableDTO;

import java.util.List;

public record RestaurantTableResponse(
        List<RestaurantTableDTO> freeTables,
        List<RestaurantTableDTO> occupiedTables,
        List<RestaurantTableDTO> recommendationTables
) {
}
