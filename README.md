# Design airline reservation system
World Plane, Inc. (hereafter identified as WPI) wishes to evaluate the feasibility of transitioning our Travel Agency airline travel reservation system to a Retail Customer airline reservation system. In this project, a proof of concept Airline Reservation System will be built based on a small scale data-set in WPI server. 
This project is designed by using MVC architecture and implemented byb JAVA.

## Core System Functionalities
- System shall allow a customer make an airline reservation to travel from a departure airport to a destination airport of their choice.
- The system shall allow a customer to search for trips with a valid three character identifier of the departing and arriving airport.
- The system shall accept a date for departing date and return date, for round trips, in MM/DD/YYYY format.
- The system will ensure the return date is after the departing date.
- System shall allow a customer to book a trip from a departure airport to an arrival airport using a series of connecting flights with a maximum of two stopovers.
- System shall allow a customer to make an airline reservation to travel either one-way between two airports or reserve a round-trip between two airports.
- System shall allow customers to choose their type of seat: first-class seating or coach seating.
- System shall present a list of trips including direct flights and connecting flights with up to two layovers.
- System shall limit layovers to no less than thirty minutes and no more than four hours. The system shall not display layovers outside these bounds.
- System shall allow the user to sort list of trips by price in USD, local departure time, local arrival time and travel time.
- System shall respond when seating is not available.
---
## Static Model
![model](https://github.com/kaito4213/Muse/blob/master/model.PNG)

## Verification



