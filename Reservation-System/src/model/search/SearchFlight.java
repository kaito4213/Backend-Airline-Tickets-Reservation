package model.search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.TimeZone;
import java.util.HashMap;

import dao.ServerInterface;
import model.airplane.Airplane;
import model.airplane.Airplanes;
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
	private final float MIN_LAYOVER_INMINUTES = 30;
	private final float MAX_LAYOVER_INMINUTES = 240;
	private static HashMap<String, Integer> coachSeatsMap;
	private static HashMap<String, Integer> firstClassSeatsMap;
	
	private HashMap<String, Flights> TodayFlightsMap = new HashMap<String, Flights>();
	private HashMap<String, Flights> TomorrowFlightsMap = new HashMap<String, Flights>();

	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 */	
	public SearchFlight() {
		mDepartureAirportCode = "";
		mArrivalAirportCode = "";
		mDepartureDate = "";
		mSeatPreference = "";
		isStopOver = false;
		coachSeatsMap = this.getCoachSearMap();
		firstClassSeatsMap = this.getFirstClassSeatsMap();
	}
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values
	 *  
	 * @param departure Three character code of the departure airport
	 * @param arrival Three character code of the arrival airport
	 * @param date Date of departure
	 * @param seat Preference of seat class on the airplane
	 * @param stop Number of stopOver from departure to arrival
	 * 
	 */
	public SearchFlight (String departure, String arrival, String date, String seat, boolean stop) {
		mDepartureAirportCode = departure;
		mArrivalAirportCode = arrival;
		mDepartureDate = date;
		mSeatPreference = seat;
		isStopOver = stop;
		coachSeatsMap = this.getCoachSearMap();
		firstClassSeatsMap = this.getFirstClassSeatsMap();
	}
	
	/**
	 * get the available coach seats for each type of airplane
	 * @return HashMap that contains the model as key, number of available coach seats as value
	 */
	public HashMap<String, Integer> getCoachSearMap(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Airplanes mAirplanes = ServerInterface.INSTANCE.getAirplanes(mTeamName);
		for (Airplane plane : mAirplanes) {
			if (!map.containsKey(plane.getModel())){
				map.put(plane.getModel(), plane.getCoachSeats());
			}
		}
		
		return map;
	}
	
	/**
	 * get the available first class seats for each type of airplane
	 * @return HashMap that contains the model as key, number of available first class seats as value
	 */
	public HashMap<String, Integer> getFirstClassSeatsMap(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Airplanes mAirplanes = ServerInterface.INSTANCE.getAirplanes(mTeamName);
		for (Airplane plane : mAirplanes) {
			if (!map.containsKey(plane.getModel())){
				map.put(plane.getModel(), plane.getFirstClassSeats());
			}
		}
		
		return map;
	}
	
	
	/**
	 * Convert object to printable string 
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
	
	
	public boolean isAvailableSeat(Flight flight){
		String type = flight.getAirplane();
		int bookedSeat = 0;
		int availableSeat = 0;
		
		if (mSeatPreference == "Coach") {
			bookedSeat = flight.getCoachBooked();
			availableSeat = coachSeatsMap.get(type);
		} else {
			bookedSeat = flight.getFirstClassBooked();
			availableSeat = firstClassSeatsMap.get(type);
		}
		
		return bookedSeat >= availableSeat ? false : true;
	}

	/**
	 * This method checks if a flight departs during the correct day after 
	 * local time conversion
	 * 
	 * @param flight in question
	 * @return true if date of flight is correct date when converted to local time
	 * @throws ParseException if the date parsing fails
	 */
	public boolean isLocalTimeSameDay(Flight flight) throws ParseException {
		String depDay = flight.getLocalDepTime();
		
		// check days
		String[] tokens1 = depDay.split(" ");
		int day1 = Integer.parseInt(tokens1[2]);
		String[] tokens2 = mDepartureDate.split("_");
		int day2 = Integer.parseInt(tokens2[2]);
		
		if (day1==day2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method adds one day to date given as the parameter.
	 * 
	 * @param date represents the date  in "yyyy MMM dd HH:mm z" string format.
	 * @return date represents in "yyyy MMM dd HH:mm z" string format.
	 * @throws ParseException if the date parsing fails
	 */
	public String addDay(String date) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM dd HH:mm z", Locale.US);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.setTime(formatter.parse(date));
		calendar.add(Calendar.DATE, 1);
		return formatter.format(calendar.getTime());
	}
	
	
	/**
	 * This method checks for the constraints that the layOver time in the range of (0.5hr, 4hr).
	 * @param arrivalTime arrival time of last flight in "yyyy MMM dd HH:mm z" format.
	 * @param departureTime departure time of next flight in "yyyy MMM dd HH:mm z" format.
	 * @return true if satisfies layOver time constraints.
	 * @throws ParseException is thrown when the date parsing fails
	 */
	public boolean isValidStopOver(String arrivalTime,String departureTime) throws ParseException{
		DateTimeFormatter mformatter = DateTimeFormatter.ofPattern("yyyy MMM d HH:mm z",Locale.US);
		
		LocalDateTime departTimeLocal = LocalDateTime.parse(departureTime, mformatter);
		LocalDateTime arrivalTimeLocal = LocalDateTime.parse(arrivalTime, mformatter);
		
		float diffInMinutes = java.time.Duration.between(arrivalTimeLocal, departTimeLocal).toMinutes();
		
		if (diffInMinutes < MIN_LAYOVER_INMINUTES || diffInMinutes > MAX_LAYOVER_INMINUTES) {
			return false;
		}
		return true;
	}
	 
	
	/**
	 * This method converts a date string from "yyyy MMM dd HH:mm z" format to "yyyy_MM_dd" format.
	 * 
	 * @param date String in "yyyy MMM dd HH:mm z" format.
	 * @return String in "yyyy_MM_dd" format.
	 * @throws ParseException if the date parsing fails
	 */
	public String dateFormatter(String date) throws ParseException{

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM dd HH:mm z", Locale.US);
		SimpleDateFormat departureDateFormatter = new SimpleDateFormat("yyyy_MM_dd",Locale.US);
		departureDateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		String departuredate = departureDateFormatter.format(formatter.parse(date));
		return departuredate;
	}
	
	/**
	 * This method converts local time to GMT time
	 * 
	 * @param String in "yyyy_MM_dd" format.
	 */
	
	/**
	 * This method copy current Flights object to a new Flights object and add new flight into the copy one
	 * 
	 * @param flights current flights object
	 * @param newFlight new flight
	 * @return result the copied flights object which adds the new flight
	 */
	public Flights addFlight(Flights flights, Flight newFlight){
		Flights result = new Flights();
		for (Flight f : flights){
			result.add(f);
		}
		result.add(newFlight);
		return result;
	}
	
	
	/**
	 * This method check the next day flights for any flight that arrives after 20:00.
	 * 
	 * @param arrivalTime represents the arrival time of a flight in "yyyy MMM dd HH:mm z" string format.
	 * @return true if arrival time of a flight is after 9pm else returns false boolean value.
	 * @throws ParseException if the date parsing fails
	 */
	public boolean checkNextDay(String arrivalTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM dd HH:mm z", Locale.US);
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.setTime(formatter.parse(arrivalTime));
		int hour = calendar.get(Calendar.HOUR);
		
		if(hour >= 20){
			return true;
		} 
		return false;
	}
	
	/**
	 * This method check if the connected flight is valid. It will both check seats and lay over time.
	 * @param lastFlight represents the first flight arrives at the airport
	 * @param nextFlight represents the second flight departs from the airport
	 * @return true if connected flight is valid, false if connected flight is invalid
	 * @throws ParseException if the date parsing fails
	 */
	public boolean checkValidConnectedFlight(Flight lastFlight, Flight nextFlight) throws ParseException {
		String arrival = lastFlight.getArrivalAirportTime();
		String depart = nextFlight.getDepartureAirportTime();
		return isValidStopOver(arrival, depart) && isAvailableSeat(nextFlight);
	}


	/**
	 * This method search all the flights that satisfies user requirement
	 * 
	 * @return list of Flights depart from mDepartureAirportCode and arrive at mArrivalAirportCode on mDepartureDate
	 * @throws ParseException if the date parsing fails
	 */
	public List<Flights> search() throws ParseException {
		int stop = 0;
		List<Flights> result = new ArrayList<Flights>();
		Flights flights = ServerInterface.INSTANCE.getFlights(mTeamName, mDepartureAirportCode, mDepartureDate);
		Queue<Flights> currentFlightsQ = new LinkedList<Flights>();
		
		//check flight from mDepartureAirportCode on mDepartureDate, if there is any available seat.
		for (Flight flight : flights) {
			if (!isAvailableSeat(flight)) {
				continue;			
			}
			if (!isLocalTimeSameDay(flight)) {
				continue;			
			}
			
			Flights newFlights = new Flights();
			newFlights.add(flight);
			
			if (newFlights.get(newFlights.size() - 1).getArrivalAirport().equals(mArrivalAirportCode)){
				result.add(newFlights);
			} else {
				currentFlightsQ.add(newFlights);
			}		
		}
		
		//if customer doesn't want to stop, return the direct flights
		if (!isStopOver) {
			return result;
		}
		
		
		//search connected flights
		while(stop < maxStopOver && !currentFlightsQ.isEmpty()) {
			Queue<Flights> nextFlightsQ = new LinkedList<Flights>();
			
			while(!currentFlightsQ.isEmpty()) {
				Flights currentFlights = currentFlightsQ.poll();	
				Flight lastFlight = currentFlights.get(currentFlights.size() - 1);
				String nextDeparture = lastFlight.getArrivalAirport();
				String date = dateFormatter(lastFlight.getArrivalAirportTime());	
				Flights	nextFlights;
				if (TodayFlightsMap.get(nextDeparture)!=null) {
					nextFlights =  TodayFlightsMap.get(nextDeparture);
				}
				else {
					nextFlights = ServerInterface.INSTANCE.getFlights(mTeamName, nextDeparture, date);
					TodayFlightsMap.put(nextDeparture,nextFlights);
				}
				
				
				
				if (checkNextDay(lastFlight.getArrivalAirportTime())) {
					String nextDay = dateFormatter(addDay(date));		
					Flights nextDayFlights;
					if (TomorrowFlightsMap.get(nextDeparture)!=null) {
						nextDayFlights =  TomorrowFlightsMap.get(nextDeparture);
					}
					else {
						nextDayFlights = ServerInterface.INSTANCE.getFlights(mTeamName, nextDeparture, nextDay);
						TomorrowFlightsMap.put(nextDeparture,nextDayFlights);
					}
					nextFlights.addAll(nextDayFlights);
				}		
					
				for (Flight flight : nextFlights) {		
					if (!checkValidConnectedFlight(lastFlight, flight)) {
						continue;
					}

					if (flight.getArrivalAirport().equals(mArrivalAirportCode)){
						result.add(addFlight(currentFlights, flight));				
					} else {
						nextFlightsQ.add(addFlight(currentFlights, flight));
					}			
				}
			 }
			 
			currentFlightsQ = nextFlightsQ;
			stop++;		
		}

		return result;
	}	
}