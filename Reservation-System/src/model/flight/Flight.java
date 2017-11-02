package model.flight;

public class Flight {
	
	private int number;
	private int flightTime;
	private String airplane;
	private String departureAirport;
	private String departureAirportTime;
	private String arrivalAirport;
	private String arrivalAirportTime;
	private int firstClassBooked;
	private int coachBooked;
	private String firstClassPrice;
	private String coachPrice;
	
        
    // the Flight class has
    // one constructor
    public Flight() {
        number = 0;
        departureAirport = "";
        departureAirport = "";
    }
        
    // Flight Class Methods
    public void setNumber(int newValue) {
        number = newValue;
    }
    public void setFlightTime(int newValue) {
        flightTime = newValue;
    }
    public void setAirplane(String newValue) {
        airplane = newValue;
    }
    public void setDepartureAirport(String newValue) {
    	departureAirport = newValue;
    }
    public void setDepartureTime(String newValue) {
    	departureAirportTime = newValue;
    }
    public void setArrivalAirport(String newValue) {
    	arrivalAirport = newValue;
    }
    public void setArrivalTime(String newValue) {
    	arrivalAirportTime = newValue;
    }
    public void setFirstClassBooked(int newValue) {
    	firstClassBooked = newValue;
    }
    public void setCoachBooked(int newValue) {
    	coachBooked = newValue;
    }
    public void setFirstClassPrice(String newValue) {
    	firstClassPrice = newValue;
    }
    public void setCoachPrice(String newValue) {
    	coachPrice = newValue;
    }
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(number).append(", ");
		sb.append(departureAirport).append(", ");
		sb.append(departureAirportTime).append(", ");
		sb.append(arrivalAirport).append(", ");
		sb.append(arrivalAirportTime).append(", ");
		sb.append("(Flight time: ").append(flightTime).append(", ");
		sb.append("Airplane model: ").append(airplane).append(", ");
		sb.append("# First Class Booked: ").append(firstClassBooked).append(", ");
		sb.append("First Class Price: ").append(firstClassPrice).append(", ");
		sb.append("# Coach Booked: ").append(coachBooked).append(", ");
		sb.append("Coach Price: ").append(coachPrice).append(")");

		return sb.toString();
	}
	
	/**
	 * Determine if object instance has valid attribute data
	 * 
	 * Verifies the name is not null and not an empty string. Verifies code is 3 characters in length.
	 * Verifies latitude is between +90.0 north pole and -90.0 south pole.
	 * Verifies longitude is between +180.0 east prime meridian and -180.0 west prime meridian.
	 * 
	 * @return true if object passes above validation checks
	 * 
	 */
	public boolean isValid() {
		
		// If the number isn't valid, the object isn't valid
		if (number == 0 )
			return false;
		
		// If we don't have a departure airport, object isn't valid
		if (departureAirport == null)
			return false;
		
		// If we don't have an arrival airport, object isn't valid
		if (arrivalAirport == null)
			return false;
		
		return true;
	}
        
}
