package model.flight;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import java.util.Locale;

import model.airport.Airport;
import model.airport.Airports;

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
	
        
	/**
	 * default constructor
	 */
    public Flight() {
        number = 0;
        departureAirport = "";
        departureAirport = "";
    }
        

    /**
	 * @param newValue the number to set
	 */
    public void setNumber(int newValue) {
        number = newValue;
    }
    
    /**
	 * @param newValue the flightTime to set
	 */
    public void setFlightTime(int newValue) {
        flightTime = newValue;
    }
    
    /**
	 * @param newValue the airplane to set
	 */
    public void setAirplane(String newValue) {
        airplane = newValue;
    }
    
    /**
	 * @param newValue the departureAirport to set
	 */
    public void setDepartureAirport(String newValue) {
    	departureAirport = newValue;
    }
    
    /**
	 * @param newValue the departureTime to set
	 */
    public void setDepartureTime(String newValue) {
    	departureAirportTime = newValue;
    }
    
    /**
	 * @param newValue the arrivalAirport to set
	 */
    public void setArrivalAirport(String newValue) {
    	arrivalAirport = newValue;
    }
    
    /**
	 * @param newValue the arrivalAirportTime to set
	 */
    public void setArrivalTime(String newValue) {
    	arrivalAirportTime = newValue;
    }
    
    /**
	 * @param newValue the firstClassBooked to set
	 */
    public void setFirstClassBooked(int newValue) {
    	firstClassBooked = newValue;
    }
    
    /**
	 * @param newValue the coachBooked to set
	 */
    public void setCoachBooked(int newValue) {
    	coachBooked = newValue;
    }
    
    /**
	 * @param newValue the firstClassPrice to set
	 */
    public void setFirstClassPrice(String newValue) {
    	firstClassPrice = newValue;
    }
    
    /**
	 * @param newValue the coachPrice to set
	 */
    public void setCoachPrice(String newValue) {
    	coachPrice = newValue;
    }
    
    /**
	 * @return the number
	 */
    public int getNumber() {
		return number;
	}
    
    /**
	 * @return the flightTime
	 */
	public int getFlightTime() {
		return flightTime;
	}
	
	/**
	 * @return the airplane
	 */
	public String getAirplane() {
		return airplane;
	}
	
	/**
	 * @return the departureAirport
	 */
	public String getDepartureAirport() {
		return departureAirport;
	}
	
	/**
	 * @return the departureAirportTime
	 */
	public String getDepartureAirportTime() {
		return departureAirportTime;
	}
	
	/**
	 * @return the arrivalAirport
	 */
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	
	/**
	 * @return the arrivalAirportTime
	 */
	public String getArrivalAirportTime() {
		return arrivalAirportTime;
	}
	
	/**
	 * @return the firstClassBooked
	 */
	public int getFirstClassBooked() {
		return firstClassBooked;
	}
	
	/**
	 * @return the coachBooked
	 */
	public int getCoachBooked() {
		return coachBooked;
	}
	
	/**
	 * @return the firstClassPrice
	 */
	public String getFirstClassPrice() {
		return firstClassPrice;
	}
	
	/**
	 * @return the coachPrice
	 */
	public String getCoachPrice() {
		return coachPrice;
	}


	/**
	 * Returns departure time in local time
	 * 
	 * @return departure time in local time
	 * @throws ParseException if the date parsing fails
	 */
	public String getLocalDepTime() throws ParseException {
		Airport depAirport = null;
		String localTime;
		
		// Get time zone for departing airport
		for(Airport airport : Airports.getInstance()) {
			String name = airport.code();
	        if(name.equals(departureAirport)) {
	            depAirport = airport;
	            break;
	        }
	    }

		localTime =  formattedLocalTime(depAirport.timezone(),departureAirportTime);

		return localTime;
	}

	/**
	 * Returns arrival time in local time
	 * 
	 * @return arrival time in local time
	 * @throws ParseException if the date parsing fails
	 */
	public String getLocalArrTime() throws ParseException {
		Airport arrAirport = null;
		String localTime;

		// Get time zone for arriving airport
		for(Airport airport : Airports.getInstance()) {
			String airportCode = airport.code();
	        if(airportCode.equals(arrivalAirport)) {
	            arrAirport = airport;
	            break;
	        }
	    }
		
		localTime =  formattedLocalTime(arrAirport.timezone(),arrivalAirportTime);
		return localTime;
	}
	
	/**
	 * Returns local time zone
	 * 
	 * @return time zone
	 * @throws ParseException if the date parsing fails
	 */
	public String getLocalTimeZone() throws ParseException {
		Airport arrAirport = null;

		// Get time zone for arriving airport
		for(Airport airport : Airports.getInstance()) {
			String airportCode = airport.code();
	        if(airportCode.equals(departureAirport)) {
	            arrAirport = airport;
	            break;
	        }
	    }
	
		return arrAirport.timezone();
	}
	
	
	/**
	 * Returns any time in local time
	 * @param timeZone local time zone of current airport
	 * @param sDate departure date or arrival date in "yyyy MMM dd HH:mm z" format
	 * @return any time in local time
	 * @throws ParseException if the date parsing fails
	 */
	public String formattedLocalTime(String timeZone,String sDate) throws ParseException {
		GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
		
	    Date date = new SimpleDateFormat("yyyy MMM dd HH:mm z",Locale.US).parse(sDate);  
		calendar.setTime(date);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM dd HH:mm z",Locale.US);
		formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
		String time = formatter.format(calendar.getTime());
		return time;
	}

	/**
	 * print flight class
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(number).append(", ");
		sb.append(departureAirport).append(", ");
		try {
			sb.append(getLocalTimeZone()).append(", ");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sb.append(getLocalDepTime()).append("\n ");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sb.append(arrivalAirport).append(", ");
		try {
			sb.append(getLocalArrTime()).append("\n ");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		sb.append("(Flight time: ").append(flightTime).append(", ");
		sb.append("Airplane model: ").append(airplane).append(", ");
		sb.append("# First Class Booked: ").append(firstClassBooked).append(", ");
		sb.append("First Class Price: ").append(firstClassPrice).append(", ");
		sb.append("# Coach Booked: ").append(coachBooked).append(", ");
		sb.append("Coach Price: ").append(coachPrice).append(")");
		*/
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