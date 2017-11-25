package model.airport;

import static org.junit.Assert.*;

import org.junit.*;

import dao.ServerInterface;

public class AirportTest {

	@SuppressWarnings("static-access")
	@Test
	public void testIsValidInputCode() {
		Airport airport = new Airport();

		// import Airports
		ServerInterface.INSTANCE.getAirports("Muse");
		
		// Test cases
		assertTrue(airport.isValidInputCode("BOS"));
		assertFalse(airport.isValidInputCode(""));
		assertFalse(airport.isValidInputCode("lskdjflskdjf"));
	}

}
