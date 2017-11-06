package view;

import java.util.Scanner;
/**
 * This class implements search page user input interface. 
 */
public class InputView {
	
	private String inputDepartureAirportCode;		// Three character code of the departure airport
	private String inputArrivalAirportCode;			// Three character code of the arrival airport
	private String inputDepartureDate;				// date of departure
	private String inputSeatPreference;				// preference of seat class on the airplane
	private String inputHasStopOver;				// if there is stopOver from departure to arrival
	private String inputIsRoundTrip;				// 
	
	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 * 
	 * @pre None
	 * @post member attributes are initialized to invalid default values
	 */	
	public InputView () {
		inputDepartureAirportCode = "";
		inputArrivalAirportCode = "";
		inputDepartureDate = "";
		inputSeatPreference = "";
		inputHasStopOver = "";
		inputIsRoundTrip = "";
	}
	
	public void setUserSearchInput() {
		String input;
		Scanner scan= new Scanner(System.in);
		System.out.println("please input your departure airport code: ");
		input = scan.nextLine();
		departureAirportCode(input);
		
		System.out.println("please input your arrival airport code: ");
		input = scan.nextLine();
		arrivalAirportCode(input);
		
		System.out.println("please input your departure date in the format of yyyy_mm_dd: ");
		input = scan.nextLine();
		departureDate(input);
		
		System.out.println("please input your preference seat (FirstClass/Coach): ");
		input = scan.nextLine();
		seatPreference(input);
		
		System.out.println("Do you want to have stopover between the departure and destination(yes/no): ");
		input = scan.nextLine();
		hasStopOver(input);
		
		System.out.println("Do you want a round trip(yes/no): ");
		input = scan.nextLine();
		isRoundTrip(input);
		
		scan.close();
	}
	
	/**
	 * set the input of departure airport
	 * 
	 * @param departureAirportCode The input of 3 letter code for the departure airport
	 */
	public void departureAirportCode (String departureAirportCode) {
		inputDepartureAirportCode = departureAirportCode;	
	}
	
	/**
	 * get the input of departure airport
	 * @return 
	 * 
	 */
	public String getDepartureAirportCode () {
		return inputDepartureAirportCode;	
	}
	
	
	/**
	 * set the input of arrival airport
	 * 
	 * @param arrivalAirportCode The input of 3 letter code for the arrival airport
	 */
	public void arrivalAirportCode (String arrivalAirportCode) {
		inputArrivalAirportCode = arrivalAirportCode;	
	}
	
	/**
	 * get the input of arrival airport
	 * @return 
	 * 
	 */
	public String getArrivalAirportCode () {
		return inputArrivalAirportCode;	
	}
	
	/**
	 * set the input of departure date
	 * 
	 * @param departureDate The input of departure date
	 */
	public void departureDate (String departureDate) {
		inputDepartureDate = departureDate;		
	}
	
	/**
	 * get the input of departure date
	 * 
	 */
	public String getDepartureDate () {
		return inputDepartureDate;		
	}
	
	/**
	 * set the input of seat preference
	 * 
	 * @param seatPreference The input of seat preference
	 */
	public void seatPreference (String seatPreference) {
		inputSeatPreference = seatPreference;
	}
	
	/**
	 * get the seat preference
	 */
	
	public String getSeatPreference() {
		return inputSeatPreference;
	}
	
	/**
	 * set the input of stopOver
	 * 
	 * @param hasStopOver The input of if there is stopOver or not
	 */
	public void hasStopOver (String hasStopOver) {
		inputHasStopOver = hasStopOver;		
	}
	
	/**
	 * get the input of stopOver
	 */
	
	public String getHasStopOver() {
		return inputHasStopOver;
	}
	
	/**
	 * set the input of round trip
	 * 
	 * @param isRoundTrip The input of if it is round-way trip or not
	 */
	public void isRoundTrip (String isRoundTrip) {
		inputIsRoundTrip = isRoundTrip;		
	}
	
	/**
	 * get the input of round trip
	 */
	
	public String getIsRoundTrip() {
		return inputIsRoundTrip;
	}
	

}

