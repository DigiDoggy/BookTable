package tablebook.backend.dto;

import tablebook.backend.entity.RestaurantTable;
import tablebook.backend.enums.Zone;


public record RestaurantTableDTO(
        String tableNumber,
        int capacity,
        int xPosition,
        int yPosition,
        Zone zone,
        boolean nearWindow,
        boolean nearPlayArea,
        boolean privacy,
        boolean occupied
) {
    public static RestaurantTableDTO toDTO(RestaurantTable table) {
        return new RestaurantTableDTO(
                table.getTableNumber(),
                table.getCapacity(),
                table.getXPosition(),
                table.getYPosition(),
                table.getZone(),
                table.isNearWindow(),
                table.isNearPlayArea(),
                table.isPrivacy(),
                table.isOccupied()
        );
    }
}
