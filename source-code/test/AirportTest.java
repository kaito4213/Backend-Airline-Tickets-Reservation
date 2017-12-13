package model.airport;

import static org.junit.Assert.*;

import org.junit.*;

<<<<<<< HEAD
import dao.Dao;
=======
>>>>>>> 44c25429425244e93f51bb3c5c15ba8ed3fdb7c6
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

	@Test
	public void testQuickTimeZone() {

		// import Airports
		ServerInterface.INSTANCE.getAirports("Muse");

		for (Airport airport : Airports.getInstance()) {
			String quickTimeZone = airport.quickTimeZone();
			
			StringBuffer timeZoneResult = ServerInterface.INSTANCE.trySetup("","timezone", airport,null,null);
			String xmlAirportTimeZone;xmlAirportTimeZone = timeZoneResult.toString();
			Dao.addAirportTimeZone(xmlAirportTimeZone, airport);
			
			assertEquals(quickTimeZone,airport.timezone());
		}
	}
}
