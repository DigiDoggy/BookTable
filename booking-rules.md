## Booking Rules

### Time slots

- **Morning**: 09:00–11:00  
- **Day**: 11:00–17:00  
- **Evening**: 17:00–23:00  

### Creating a reservation (customer)

- **Input**: the customer selects date, time, number of people and (optionally) a zone.  
- **Availability**: the system shows only suitable and available tables (by time and capacity).  
- **Top 3 suggestions**: based on the customer’s preferences (e.g. zone, table size, location), the system highlights the 3 best matching tables. All other available tables are still selectable but are not highlighted.  
- **Selection**: the customer selects one table (the selected table is highlighted in green), confirms the reservation and provides contact details (phone/email).  

### Reservation duration

- **Morning and day**: the reservation is valid for **2 hours** from the selected time, then it is automatically released.  
- **Evening**: the reservation is valid **until 23:00** or until staff cancels it.  

### Table constraints and availability

- A table is shown as suitable only if its maximum capacity is **greater than or equal to** the number of people.  
- One table at a given time can be reserved by **only one customer**.  
- When a table is reserved for a given time slot, other customers cannot choose this table for overlapping times.  

### Staff capabilities

- Staff can create a reservation on behalf of a customer (e.g. by phone) with the same parameters (date, time, people, zone, table) plus an internal note (e.g. birthday, date, business meeting).  
- Staff can cancel any existing reservation at any time.  

### Notification

- After confirming the reservation, the customer receives a notification (SMS or e-mail) containing the date, time, table, number of people, and basic reservation details.  

