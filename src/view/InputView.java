package view;

import java.util.Scanner;

import model.airport.Airport;
/**
 * This class implements search page user input interface. 
 */
/**
 * @param inputDepartureAirportCode
 * @param inputArrivalAirportCode
 * @param inputDepartureDate
 * @param inputReturnDate
 * @param inputSeatPreference
 * @param inputHasStopOver
 * @param inputIsRoundTrip
 */
public class InputView {
	
	private String inputDepartureAirportCode;		// Three character code of the departure airport
	private String inputArrivalAirportCode;			// Three character code of the arrival airport
	private String inputDepartureDate;				// date of departure
	private String inputReturnDate;					// date of return
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
		inputReturnDate = "";
		inputSeatPreference = "";
		inputHasStopOver = "";
		inputIsRoundTrip = "";
	}
	
	public void setUserSearchInput() {
		Scanner scan = new Scanner(System.in);

		requestValidDepAirport(scan);
		requestValidArrAirport(scan);
		requestValidDepDate(scan);
		requestValidClass(scan);
		requestValidStopOver(scan);
		requestValidRoundTrip(scan);
		
		if (inputIsRoundTrip.equals("yes")) {
			requestValidRetDate(scan);
		}

		//scan.close();
	}
	
	/**
	 * Request departure airport and set if valid
	 * 
	 */
	public void requestValidDepAirport(Scanner scan) {
		String input;
		
		do {
			System.out.println("***************Please input your departure airport code***************");
			input = scan.nextLine();
			departureAirportCode(input);
			}while(!Airport.isValidInputCode(input));
	}
	
	/**
	 * Request arrival airport and set if valid
	 * 
	 */
	public void requestValidArrAirport(Scanner scan) {
		String input;
		
		do {
			System.out.println("***************please input your arrival airport code***************");
			input = scan.nextLine();
			arrivalAirportCode(input);
			}while(!Airport.isValidInputCode(input));
	}
	
	/**
	 * Request departure date and set if valid
	 * 
	 */
	public void requestValidDepDate(Scanner scan) {
		String input, parsedInput;
		boolean validDepDate = false;
		
		do {
			
			System.out.println("**********Please input your departure date in the format of mm/dd/yyyy*********** ");
			input = scan.nextLine();
			parsedInput = checkValidDate(input);
			
			
			if ( parsedInput != null ) {
				departureDate(parsedInput);
				validDepDate = true;
			}
			
		}while(!validDepDate);
	}
	
	/**
	 * Request return date and set if valid
	 * 
	 */
	public void requestValidRetDate(Scanner scan) {
		String input, parsedInput;
		boolean validRetDate = false;
		boolean dateValid;
		
		do {
			
			System.out.println("*************Please input your return date in the format of mm/dd/yyyy*************");
			input = scan.nextLine();
			parsedInput = checkValidDate(input);
			dateValid = ensureRetAfterDep(parsedInput);		
			
			if ( (parsedInput != null) && (dateValid == true) ) {
				returnDate(parsedInput);
				validRetDate = true;
			}
			
		}while(!validRetDate);
	}

	/**
	 * Ensures return date is after departure date
	 * 
	 * @return true if valid return date, false if invalid
	 */

	public boolean ensureRetAfterDep(String retDate) {
		int day1, day2, month1, month2, year1, year2;
		String depDate = inputDepartureDate;
		
		// Parse departure date
		String[] tokens = depDate.split("_");
		month1 = Integer.parseInt(tokens[0]);
		day1 = Integer.parseInt(tokens[1]);
		year1 = Integer.parseInt(tokens[2]);
		
		// Parse return date

		tokens = retDate.split("_");
		month2 = Integer.parseInt(tokens[0]);
		day2 = Integer.parseInt(tokens[1]);
		year2 = Integer.parseInt(tokens[2]);
		
		// Compare values
		if (year2 < year1) {
			return false;
		} else if (month2 < month1) {
			return false;
		} else if (day2 < day1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Verifies that date input matches input format and is within range
	 * 
	 * @return formatted date for get request or null if invalid
	 */
	public String checkValidDate(String input) {
		String[] tokens = input.split("/");
		
		if (tokens.length != 3) {
			return null;
		}
		
		int month = Integer.parseInt(tokens[0]);
		int day = Integer.parseInt(tokens[1]);
		int year = Integer.parseInt(tokens[2]);
		
		if ( (month == 12) && (day >= 5) && (day <= 31) && (year == 2017) ) {
			return (year + "_" + month + "_" + day);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Request first class or coach and set if valid
	 * 
	 */
	public void requestValidClass(Scanner scan) {
		String input;
		boolean validClass = false;
		
		do {
			System.out.println("***************Please input your preference seat (First Class/Coach)***************");
			input = scan.nextLine();
			
			if (input.toUpperCase().equals("COACH")) {
				validClass = true;
				seatPreference("Coach");
			}
			else if (input.toUpperCase().equals("FIRST CLASS")) {
				validClass = true;
				seatPreference("FirstClass");
			}
		}while(!validClass);
	}
	
	/**
	 * Request stop over yes/no and set if valid
	 * 
	 */
	public void requestValidStopOver(Scanner scan) {
		String input;
		boolean validStopOver = false;
		do {
			System.out.println("*********Do you want to have stopover between the departure and destination(yes/no)*********");
			input = scan.nextLine();

			if (input.toUpperCase().equals("NO") || input.toUpperCase().equals("YES")) {
				hasStopOver(input.toLowerCase());
				validStopOver = true;
			} 
		}while(!validStopOver);
	}
	
	/**
	 * Request round trip yes/no and set if valid
	 * 
	 */
	public void requestValidRoundTrip(Scanner scan) {
		String input;
		boolean validRoundTrip = false;
		
		do {
			System.out.println("***************Do you want a round trip(yes/no)***************");
			input = scan.nextLine();
			
			if (input.toUpperCase().equals("NO") || input.toUpperCase().equals("YES")) {
				isRoundTrip(input.toLowerCase());
				validRoundTrip = true;
			} 
		}while(!validRoundTrip);
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
	 * set the input of return date
	 * 
	 * @param departureDate The input of departure date
	 */
	public void returnDate (String returnDate) {
		inputReturnDate = returnDate;		
	}
	
	/**
	 * get the input of return date
	 * 
	 */
	public String getReturnDate () {
		return inputReturnDate;		
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

