package model.search;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.flight.Flights;
import model.reservation.Reservation;
import model.reservation.Reservations;
import model.search.SearchFlight;

/**
 * This class search both of one-way trip and round-way trip that satisfy customer's requirement.
 * Class member attributes are the same as defined by the view.
 */

public class SearchFlights {
	private String mDepartureAirportCode;		// Three character code of the departure airport
	private String mArrivalAirportCode;			// Three character code of the arrival airport
	private String mDepartureDate;				// date of departure
	private String mReturnDate;					// date of return
	private String mSeatPreference;				// preference of seat class on the airplane
	private boolean isStopOver;					// whether has stop over or not
	private boolean isRoundTrip;				// whether is round trip or one way trip
	
	/**
	 * Default constructor
	 * 
	 * Constructor without parameters. Requires object fields to be explicitly
	 */	
	public SearchFlights() {
		mDepartureAirportCode = "";
		mArrivalAirportCode = "";
		mDepartureDate = "";
		mReturnDate = "";
		mSeatPreference = "";
		isStopOver = false;
		isRoundTrip = false;	
	}
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values
	 *  
	 * @param depart Three character code of the departure airport
	 * @param arrival Three character code of the arrival airport
	 * @param departureDate Date of departure
	 * @param returnDate Date of return
	 * @param seat Preference of seat class on the airplane
	 * @param stopover if customer wants stop overs between the origin and destination
	 * @param round if customer wants return flight
	 * 
	 */
	public SearchFlights(String depart, String arrival, String departureDate, String returnDate, String seat, boolean stopover, boolean round) {
		mDepartureAirportCode = depart;
		mArrivalAirportCode = arrival;
		mDepartureDate = departureDate;
		mReturnDate = returnDate;
		mSeatPreference = seat;
		isStopOver = stopover;
		isRoundTrip = round;	
	}
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values
	 *  
	 * @param depart Three character code of the departure airport
	 * @param arrival Three character code of the arrival airport
	 * @param departureDate Date of departure
	 * @param returnDate Date of return
	 * @param seat Preference of seat class on the airplane
	 * @param stop if customer wants stop overs between the origin and destination
	 * @param round if customer wants return flight
	 * 
	 */
	public SearchFlights(String depart, String arrival, String departureDate, String returnDate, String seat, String stop, String round) {
		boolean tmpStop, tmpRound;
		if(stop.equals("yes") || stop.equals("y")) {
			tmpStop = true;
		} else {
			tmpStop = false;
		}
		
		if(round.equals("yes") || round.equals("y")) {
			tmpRound = true;
		} else {
			tmpRound = false;
		}
		
		mDepartureAirportCode = depart;
		mArrivalAirportCode = arrival;
		mDepartureDate = departureDate;
		mReturnDate = returnDate;
		mSeatPreference = seat;
		isStopOver = tmpStop;
		isRoundTrip = tmpRound;	
	}
	
	/**
	 * Convert object to printable string
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("This is search Flights class: \n");
		sb.append(mDepartureAirportCode).append(", ");
		sb.append(mArrivalAirportCode).append(", ");
		sb.append(mDepartureDate).append(", ");
		sb.append(mReturnDate).append(", ");
		sb.append(mSeatPreference).append(", ");
		sb.append(String.valueOf(isStopOver)).append(", ");
		sb.append(String.valueOf(isRoundTrip));
		
		return sb.toString();
	}
	
	/**
	 * Set the departure airport code
	 * 
	 * @param  departure Three character code of the departure airport
	 */
	public void departureAirportCode (String departure) {
		mDepartureAirportCode = departure;
	}
	
	/**
	 * get the departure airport code
	 * 
	 * @return departure airport code
	 */
	public String departureAirportCode () {
		return mDepartureAirportCode;
	}
	
	/**
	 * Set the arrival airport code
	 * 
	 * @param  arrival Three character code of the arrival airport
	 */
	public void arrivalAirportCode (String arrival) {
		mArrivalAirportCode = arrival;
	}
	
	/**
	 * get the arrival airport code
	 * 
	 * @return arrival airport code
	 */
	public String arrivalAirportCode () {
		return mArrivalAirportCode;
	}
	
	/**
	 * Set the departure date
	 * 
	 * @param date Date of departure
	 */
	
	public void departureDate (String date) {
		mDepartureDate = date;
	}
	
	/**
	 * get the departure date
	 * 
	 * @return departure date
	 */
	public String departureDate () {
		return mDepartureDate;
	}
	
	/**
	 * Set the return date
	 * 
	 * @param date Date of return
	 */
	
	public void returnDate (String date) {
		mReturnDate = date;
	}
	
	/**
	 * get the return date
	 * 
	 * @return return date
	 */
	public String returnDate () {
		return mReturnDate;
	}
	
	/**
	 * Set the preference seat
	 * 
	 * @param seat Preference of seat class on the airplane
	 */
	
	public void seatPreference (String seat) {
		mSeatPreference = seat;
	}
	
	/**
	 * get the preference seat
	 * 
	 * @return preference seat
	 */
	public String seatPreference () {
		return mSeatPreference;
	}
	
	/**
	 * Set the if there is stop between the departure and arrival airport
	 * 
	 * @param stop if there is stop between the departure and arrival airport
	 */
	
	public void stopOver (boolean stop) {
		isStopOver = stop;
	}
	
	public void stopOver (String stop) {
		boolean tmpStop;
		stop.toLowerCase();
		if(stop.equals("yes") || stop.equals("y")) {
			tmpStop = true;
		} else {
			tmpStop = false;
		}
		
		isStopOver = tmpStop;
	}
	
	/**
	 * get the stopOver
	 * 
	 * @return boolean if stop
	 */
	public boolean stopOver () {
		return isStopOver;
	}
	
	/**
	 * Set the if it is one way trip or round way trip
	 * 
	 * @param round if there is stop between the departure and arrival airport
	 */
	
	public void roundTrip (boolean round) {
		isRoundTrip = round;
	}
	
	/**
	 * Set the if it is one way trip or round way trip
	 * 
	 * @param round if there is stop between the departure and arrival airport
	 */
	public void roundTrip (String round) {
		boolean tmpRound;
		round.toLowerCase();
		if(round.equals("yes") || round.equals("y")) {
			tmpRound = true;
		} else {
			tmpRound = false;
		}
		
		isRoundTrip = tmpRound;
	}
	
	/**
	 * get the topOvers
	 * 
	 * @return boolean if stop
	 */
	public boolean roundTrip () {
		return isRoundTrip;
	}
	
	/**
	 * This method will get the search result from SearchFlight class, it will check if customer wants return trip before search the return flights
	 * 
	 * @return list of reservations that contain matched flights, it may be either one way trip or round way trip
	 * @throws ParseException if the date parsing fails
	 */
	public List<Reservations> getSearchResult() throws ParseException {
		List<Reservations> result = new ArrayList<Reservations>();
		SearchFlight outbound = new SearchFlight(mDepartureAirportCode, mArrivalAirportCode, mDepartureDate, mSeatPreference, isStopOver);
		List<Flights> outboundFlights = outbound.search();
		
		Reservations outboundReservations = new Reservations();
		int outboundReservationsIndex = 1;
		for (Flights flights : outboundFlights) {
			Reservation reservation = new Reservation(flights, mSeatPreference, outboundReservationsIndex++);
			outboundReservations.add(reservation);
		}
		result.add(outboundReservations);
		
		if (isRoundTrip) {
			SearchFlight inbound = new SearchFlight(mArrivalAirportCode, mDepartureAirportCode, mReturnDate, mSeatPreference,isStopOver);	
			List<Flights> inboundFlights = inbound.search();
			Reservations inboundReservations = new Reservations();
			int inboundReservationsIndex = 1;
			for (Flights flights : inboundFlights) {
				Reservation reservation = new Reservation(flights, mSeatPreference, inboundReservationsIndex++);
				inboundReservations.add(reservation);
			}
			result.add(inboundReservations);
		}
		return result;
	}
}