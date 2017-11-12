package model.search;

import java.util.ArrayList;
import java.util.List;

import model.flight.Flight;
import model.flight.Flights;
import model.search.SearchFlight;

public class SearchFlights {
	private String mDepartureAirportCode;		// Three character code of the departure airport
	private String mArrivalAirportCode;			// Three character code of the arrival airport
	private String mDepartureDate;				// date of departure
	private String mReturnDate;					// date of return
	private String mSeatPreference;				// preference of seat class on the airplane
	private boolean isStopOver;					// whether has stop over or not
	private boolean isRoundTrip;				// whether is round trip or one way trip
	
	//default constructor
	public SearchFlights() {
		mDepartureAirportCode = "";
		mArrivalAirportCode = "";
		mDepartureDate = "";
		mReturnDate = "";
		mSeatPreference = "";
		isStopOver = false;
		isRoundTrip = false;	
	}
	
	//Initializing constructor
	public SearchFlights(String depart, String arrival, String departureDate, String returnDate, String seat, boolean stopover, boolean round) {
		mDepartureAirportCode = depart;
		mArrivalAirportCode = arrival;
		mDepartureDate = departureDate;
		mReturnDate = returnDate;
		mSeatPreference = seat;
		isStopOver = stopover;
		isRoundTrip = round;	
	}
	
	//Initializing constructor with all params as type String. Converts isStopOver and isRoundTrip values to required boolean format.
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
	 * @param  mDepartureAirportCode Three character code of the departure airport
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
	 * @param  mArrivalAirportCode Three character code of the arrival airport
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
	 * @param mDepartureDate Date of departure
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
	 * @param mReturnDate Date of return
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
	 * @param mDepartureDate Preference of seat class on the airplane
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
	 * @param isStopOver if there is stop between the departure and arrival airport
	 */
	
	public void stopOver (boolean stop) {
		isStopOver = stop;
	}
	
	public void stopOver (String stop) {
		boolean tmpStop;
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
	 * @param isRoundTrip if there is stop between the departure and arrival airport
	 */
	
	public void roundTrip (boolean round) {
		isRoundTrip = round;
	}
	
	public void roundTrip (String round) {
		boolean tmpRound;
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
	
	public List<List<Flights>> getSearchResult() {
		List<List<Flights>> result = new ArrayList<List<Flights>>();
		SearchFlight goFlight = new SearchFlight(mDepartureAirportCode, mArrivalAirportCode, mDepartureDate, mSeatPreference, isStopOver);
		result.add(goFlight.search());
		
		if (isRoundTrip) {
			SearchFlight returnFlight = new SearchFlight(mArrivalAirportCode, mDepartureAirportCode, mReturnDate, mSeatPreference,isStopOver);	
			result.add(returnFlight.search());
		}

		return result;
	}
}