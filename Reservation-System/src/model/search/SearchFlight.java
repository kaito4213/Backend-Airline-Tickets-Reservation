package model.search;

import java.util.Date;
import java.util.*;
import model.airport.Airport;
import model.airport.Airports;
import model.flight.*;
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
	private int mStopOver;						// number of stopOver from departure to arrival
	
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
	public SearchFlight (String mDepartureAirportCode, String mArrivalAirportCode, String mDepartureDate,
						String mSeatPreference, int mStopOver) {
		
	}
	
	/**
	 * Initializing constructor with all params as type String. Converts mStopOver value to required int format.
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
	
	public SearchFlight (String mDepartureAirportCode, String mArrivalAirportCode, String mDepartureDate,
			String mSeatPreference, String mStopOver) {

	}
	
	/**
	 * Convert object to printable string of format "........"(TODO)
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}
	
	
	/**
	 * Set the departure airport code
	 * 
	 * @param  mDepartureAirportCode Three character code of the departure airport
	 */
	public void departureAirportCode (String mDepartureAirportCode) {
	
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
	public void arrivalAirportCode (String mArrivalAirportCode) {
	
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
	
	public void departureDate (String mDepartureDate) {
		
	}
	
	/**
	 * get the departure date
	 * 
	 * @return departure date
	 */
	public Date departureDate () {
		return null;
	}
	
	/**
	 * Set the preference seat
	 * 
	 * @param mDepartureDate Preference of seat class on the airplane
	 */
	
	public void seatPreference (String mSeatPreference) {
		
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
	 * Set the number of stopOver
	 * 
	 * @param mStopOver Number of stopOver from departure to arrival
	 */
	
	public void seatPreference (int mStopOver) {
		
	}
	
	/**
	 * get the stopOver
	 * 
	 * @return number of stopOver
	 */
	public int stopOver () {
		return mStopOver;
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
	
	public List<Flight> search () {
		return null;
	}
}