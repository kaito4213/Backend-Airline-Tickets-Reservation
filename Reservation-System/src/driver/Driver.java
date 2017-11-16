/**
 * 
 */
package driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Scanner;

import dao.ServerInterface;
import model.airport.Airport;
import model.airport.Airports;
import model.flight.Flight;
import model.flight.Flights;

/**
 * @author blake
 *
 */
public class Driver {

	/**
	 * Entry point for CS509 sample code driver
	 * 
	 * This driver will retrieve the list of airports from the CS509 server and print the list 
	 * to the console sorted by 3 character airport code
	 * 
	 * @param args is the arguments passed to java vm in format of "CS509.sample teamName" where teamName is a valid team
	 */
	public static void main(String[] args) {
		/**
		if (args.length != 1) {
			System.err.println("usage: CS509.sample teamName");
			System.exit(-1);
			return;
		}
		*/
		
		Scanner scan= new Scanner(System.in);
		System.out.println("please input your team name: ");
		String teamName = scan.nextLine();
		System.out.println("please input your departure airport: ");
		String departureAirport = scan.nextLine();
		System.out.println("please input your departure date in the format of yyyy_mm_dd: ");
		String departureDate = scan.nextLine();
		
		//String teamName = args[0];
		//String teamName = "Muse";
		// Try to get a list of airports
		
		// Try to get a list of airports
			Airports airports = ServerInterface.INSTANCE.getAirports(teamName);
			Collections.sort(airports);
			for (Airport airport : airports) {
				System.out.println(airport.toString());
			}

			// Try to get a list of flights
			Flights flights = ServerInterface.INSTANCE.getFlights(teamName, departureAirport, departureDate);
			//Collections.sort(flights);
			for (Flight flight : flights) {
				System.out.println(flight.toString());
			}
		
		
		// TODO: use dao object to do this instead of server interface
		// use DaoAirport
		
		// array -> ['start', 'dest']
		// new SearchController(start, dest) -> getSearchResult();
		
		/**
		 * if(cmd == search) {
		 *   searchResults = getSearchResult();
		 * }
		 * 
		 * 
		 * stop............
		 * 
		 * if(reservation) {
		 *   how to get result
		 * }
		 * 
		 * 
		 * 
		 */
		
		/*
		Airports airports = ServerInterface.INSTANCE.getAirports(teamName);
		Collections.sort(airports);
		for (Airport airport : airports) {
			System.out.println(airport.toString());
		}
		*/
		
	}
}

/* To do:
 *       Method for get local time
 *       jUnit tests
 *       Sorting
 *
 * Nice to have
 *       GUI
 *       Performance
 */
