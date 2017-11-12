/**
 * 
 */
package driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import controller.SearchController;
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
		
		//SearchController sc = new SearchController();
		/**
		List<Flights> result = new ArrayList<Flights>();
		int stop = 0;
		Flights flights = ServerInterface.INSTANCE.getFlights("Muse", "BOS", "2017_12_10");
		Queue<Flights> flightsQ = new LinkedList<Flights>();
		
		for (Flight flight : flights) {
			Flights newFlights = new Flights();
			newFlights.add(flight);
			
			if (newFlights.get(newFlights.size() - 1).getArrivalAirport().equals("JFK")){
				result.add(newFlights);
			} else {
				flightsQ.add(newFlights);
			}
		}
		System.out.println("result size: " + result.size() + ", " + flightsQ.size());
		
		while(stop < 2 && !flightsQ.isEmpty()) {
			Flights f = flightsQ.poll();
			for (Flight fl : f) {
				System.out.println("current pop out: " + fl);
			}
			
			String nextDeparture = f.get(f.size() - 1).getArrivalAirport();
			String date = f.get(f.size() - 1).getArrivalAirportTime();
			System.out.println("next dept: " + nextDeparture + "date: " + date);
			Flights nextFlights = ServerInterface.INSTANCE.getFlights("Muse", nextDeparture, date);
			
			for (Flight flight : nextFlights) {
				f.add(flight);
				if (f.get(f.size() - 1).getArrivalAirport().equals("JFK")){
					result.add(f);
				} else {
					flightsQ.add(f);
				}
				f.remove(f.size() - 1);
			}
			stop++;
		}
	*/
	}
}
