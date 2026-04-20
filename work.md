** 09.03
# Work log

See detailed booking rules in [booking-rules.md](docs/booking-rules.md).

## Initial project setup

- ERD planning
- Spring Boot initialization
- added entities: User, Reservation, RestaurantTable
- created base repositories and controllers
 

# 10.03 

- Refined booking requirements and added booking-rules.md based on common booking platform practices.
- Thinking through possible recommendations for the client.

# 11.03
- Added logic for table occupancy.
- Added recommendations for the client.
- Added server response with restaurant load information.
- Added table DTOs.


# 20.04
- Added reservation create request DTO.
- Added reservation response DTO.
- Added reservation creation endpoint and reservations fetching endpoint (by date / by table).
- Updated reservation service with overlap checks and reservation end-time calculation.
- Extended reservation repository with date and table+date queries.
- Added Docker Compose setup for Postgres and app service.
- Added Dockerfile placeholder for app containerization.
