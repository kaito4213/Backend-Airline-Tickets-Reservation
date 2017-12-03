package dao;

import static org.junit.Assert.*;

import org.junit.Test;

import model.airplane.Airplane;
import model.airplane.Airplanes;
import model.airport.Airport;
import model.airport.Airports;
import model.flight.Flight;
import model.flight.Flights;

public class ServerInterfaceTest {

	@Test
	public void testGetAirports() {
		String teamName = "Muse";
		
		// import airports
		ServerInterface.INSTANCE.getAirports(teamName);
		
		// check if valid
		for (Airport airport : Airports.getInstance()) {
			assertTrue(airport.isValid());
		}
	}

	@Test
	public void testGetFlights() {
		String teamName = "Muse";
		String airport = "BOS";
		String date = "2017_12_20";
		
		// import flights
		Flights flights = ServerInterface.INSTANCE.getFlights(teamName,airport,date);

		// check if valid
		for (Flight flight : flights) {
			assertTrue(flight.isValid());
		}
	}

	@Test
	public void testGetAirplanes() {
		String teamName = "Muse";
		
		// import airplanes
		Airplanes airplanes = ServerInterface.INSTANCE.getAirplanes(teamName);

		// check if valid
		for (Airplane airplane : airplanes) {
			assertTrue(airplane.isValid());
		}
	}

}
