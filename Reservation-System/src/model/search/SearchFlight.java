package model.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import java.util.Date;

import dao.ServerInterface;
import model.airport.Airport;
import model.airport.Airports;
import model.flight.Flight;
import model.flight.Flights;
/**
 * This class search all the flights that satisfy customer's requirement of one-way trip.
 * Class member attributes are the same as defined by the view. If customer requires return flight,
 * departure and arrival input information should be exchanged inside this class.
 */
public class SearchFlight {
	
	private String mDepartureAirportCode;		// Three character code of the departure airport
	private String mArrivalAirportCode;			// Three character code of the arrival airport
	private String mDepartureDate;				// date of departure
	private String mSeatPreference;				// preference of seat class on the airplane
	private boolean isStopOver;					// number of stopOver from departure to arrival
	private final String mTeamName = "Muse";
	private final int maxStopOver = 2;
	
	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 * 
	 * @pre None
	 * @post member attributes are initialized to invalid default values
	 */	
	public SearchFlight() {
		mDepartureAirportCode = "";
		mArrivalAirportCode = "";
		mDepartureDate = "";
		mSeatPreference = "";
		isStopOver = false;
	}
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values
	 *  
	 * @param mDepartureAirportCode Three character code of the departure airport
	 * @param mArrivalAirportCode Three character code of the arrival airport
	 * @param mDepartureDate Date of departure
	 * @param mSeatPreference Preference of seat class on the airplane
	 * @param mStopOver Number of stopOver from departure to arrival
	 * 
	 * @pre mDepartureAirportCode and mArrivalAirportCode are 3 character strings, 
	 * mDepartureDate and mSeatPreference are not empty, mStopOver is a valid value
	 * @post member attributes are initialized with input parameter values
	 * @throws IllegalArgumentException is any parameter is invalid
	 */
	public SearchFlight (String departure, String arrival, String date, String seat, boolean stop) {
		mDepartureAirportCode = departure;
		mArrivalAirportCode = arrival;
		mDepartureDate = date;
		mSeatPreference = seat;
		isStopOver = stop;
	}
	
	
	/**
	 * Convert object to printable string of format "........"(TODO)
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(mDepartureAirportCode).append(", ");
		sb.append(mArrivalAirportCode).append(", ");
		sb.append(mDepartureDate).append(", ");
		sb.append(mSeatPreference).append(", ");
		sb.append(String.valueOf(isStopOver)).append("\n ");
		
		return sb.toString();
	}

	
	/** Determine if the stopOver time is reasonable(reasonable time ? need to be more specified here)
	 * 
	 *  @param arrivalTime Local time of arrival in last flight
	 *  @param departureTime Local time of departure in next flight
	 *  @return false if unreasonable, ture if reasonable 
	 */
	public boolean isValidStopOverTime(String arrivalTime, String departureTime){
		
		return true;
	}
	
	public boolean isAvailableSeat(Flight flight){
		
		return true;
	}
	
	public void addDay (Date day) {
		
	}
	
	//DFS
	public List<Flights> search () {
		List<Flights> result = new ArrayList<Flights>();
		int stop = 0;
		Flights flights = ServerInterface.INSTANCE.getFlights(mTeamName, mDepartureAirportCode, mDepartureDate);
		Queue<Flights> flightsQ = new LinkedList<Flights>();
		
		for (Flight flight : flights) {
			Flights newFlights = new Flights();
			newFlights.add(flight);
			
			if (newFlights.get(newFlights.size() - 1).getArrivalAirport().equals(mArrivalAirportCode)){
				result.add(newFlights);
			} else {
				flightsQ.add(newFlights);
			}
		}
		
		while(stop < maxStopOver && !flightsQ.isEmpty()) {
			Flights f = flightsQ.poll();
			String nextDeparture = f.get(f.size() - 1).getArrivalAirport();
			String date = f.get(f.size() - 1).getArrivalAirportTime();
			Flights nextFlights = ServerInterface.INSTANCE.getFlights(mTeamName, nextDeparture, date);
			
			for (Flight flight : nextFlights) {
				f.add(flight);
				if (f.get(f.size() - 1).getArrivalAirport().equals(mArrivalAirportCode)){
					result.add(f);
				} else {
					flightsQ.add(f);
				}
				f.remove(f.size() - 1);
			}	
			stop++;
		}
		
		return result;
	}
	
}