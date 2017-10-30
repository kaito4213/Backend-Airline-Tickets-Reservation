/**
 * 
 */
package driver;

import java.util.Collections;

import dao.ServerInterface;
import model.airport.Airport;
import model.airport.Airports;

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
		if (args.length != 1) {
			System.err.println("usage: CS509.sample teamName");
			System.exit(-1);
			return;
		}
		
		String teamName = args[0];
		// Try to get a list of airports
		
		while(true) {
			
			
			
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
