# Create a Flight 

## Problem Statement

You are building an Airline Management System. As a part of this system, you need to expose a functionality using which airline admins can create a flight. 
Once this flight has been created, users will be able to book tickets for this flight using the system.

1. The request to book a flight will contain the following things:
   * Airplane ID - The ID of the Airplane.
   * User ID - The ID of the user who is creating the flight.
   * Airport ID - The ID of the Airport from where the flight will departure.
   * PricingConfig (List<Pair<SeatType, Double>) - The price of each seat type for this flight.
   * Start Time - The start time of the flight.
   * End Time - The end time of the flight.
2. This functionality should be only accessible to the Airport admin.
3. The functionality should do basic data validation checks ex. The start time should be before the end time.
4. Once this functionality executes successfully, we should store flight details, seats related details for this flight and pricing details for this flight in the database.


## Instructions
1. Carefully look at `CreateFlightRequestDto` and `CreateFlightResponseDto` classes. These classes represent the request and response of the functionality which we want to implement.
2. Carefully examine the models package to understand the database schema.
3. Implement the `createFlight` method inside the `FlightController`.
4. Implement the `FlightService` interface and fix the repository interfaces.
5. You might need to add annotations like `@Service`, `@Autowired`, `@Entity` etc. to make the solution work. You might also need to handle cardinality between the models.
6. We will be using H2 database which is an in-memory SQL database. You do not need to implement any database related code. You just need to use the repository interfaces to interact with the database.


