/**
 * 
 */
package airport;

import java.util.Comparator;

import utils.Saps;

/**
 * This class holds values pertaining to a single Airport. Class member attributes
 * are the same as defined by the CS509 server API and store values after conversion from
 * XML received from the server to Java primitives. Attributes are accessed via getter and 
 * setter methods.
 * 
 * @author blake
 * @version 1.2
 * @since 2016-02-24
 * 
 * 
 *
 */
public class Airport implements Comparable<Airport>, Comparator<Airport> {
	
	/**
	 * Airport attributes as defined by the CS509 server interface XML
	 */
	private String mName;              // Full name of the airport
	private String mCode;              // Three character code of the airport
	private double mLatitude;          // Latitude of airport in decimal format
	private double mLongitude;         // Longitude of the airport in decimal format
	
	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 * 
	 * @pre None
	 * @post member attributes are initialized to invalid default values
	 */	
	public Airport () {
		mName = "";
		mCode = "";
		mLatitude = Double.MAX_VALUE;
		mLongitude = Double.MAX_VALUE;
	}
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values
	 *  
	 * @param name The human readable name of the airport
	 * @param code The 3 letter code for the airport
	 * @param latitude The north/south coordinate of the airport 
	 * @param longitude The east/west coordinate of the airport
	 * 
	 * @pre code is a 3 character string, name is not empty, latitude and longitude are valid values
	 * @post member attributes are initialized with input parameter values
	 * @throws IllegalArgumentException is any parameter is invalid
	 */
	public Airport (String name, String code, double latitude, double longitude) {
		if (!isValidName(name))
			throw new IllegalArgumentException(name);
		if (!isValidCode(code)) 
			throw new IllegalArgumentException(code);
		if (!isValidLatitude(latitude))
			throw new IllegalArgumentException(Double.toString(latitude));
		if (!isValidLongitude(longitude))
			throw new IllegalArgumentException(Double.toString(longitude));
		
		mName = name;
		mCode = code;
		mLatitude = latitude;
		mLongitude = longitude;
	}
	
	/**
	 * Initializing constructor with all params as type String. Converts latitude and longitude
	 * values to required double format.
	 * 
	 * @param name The human readable name of the airport
	 * @param code The 3 letter code for the airport
	 * @param latitude is the string representation of latitude decimal format 
	 * @param longitude is the String representation of the longitude in decimal format
	 * 
	 * @pre the latitude and longitude are valid String representations of valid lat/lon values
	 * @post member attributes are initialized with input parameter values
	 * @throws IllegalArgumentException is any parameter is invalid
	 */
	public Airport (String name, String code, String latitude, String longitude) {
		double tmpLatitude, tmpLongitude;
		try {
			tmpLatitude = Double.parseDouble(latitude);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException ("Latitude must be between -90.0 and +90.0", ex);
		}
		
		try {
			tmpLongitude = Double.parseDouble(latitude);
		} catch (NullPointerException | NumberFormatException ex) {
			throw new IllegalArgumentException ("Longitude must be between -180.0 and +180.0", ex);
		}
		
		mName = name;
		mCode = code;
		mLatitude = tmpLatitude;
		mLongitude = tmpLongitude;
	}

	/**
	 * Convert object to printable string of format "Code, (lat, lon), Name"
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(mCode).append(", ");
		sb.append("(").append(String.format("%1$.3f", mLatitude)).append(", ");
		sb.append(String.format("%1$.3f", mLongitude)).append("), ");
		sb.append(mName);

		return sb.toString();
	}
	/**
	 * Set the airport name
	 * 
	 * @param name The human readable name of the airport
	 * @throws IllegalArgumentException is name is invalid
	 */
	public void name (String name) {
		if (isValidName (name))
			mName = name;
		else
			throw new IllegalArgumentException (name);
	}
	
	/**
	 * get the airport name
	 * 
	 * @return Airport name
	 */
	public String name () {
		return mName;
	}
	
	/**
	 * set the airport 3 letter code
	 * 
	 * @param code The 3 letter code for the airport
	 * @throws IllegalArgumentException is code is invalid
	 */
	public void code (String code) {
		if (isValidCode(code))
			mCode = code;
		else
			throw new IllegalArgumentException (code);
	}
	
	/**
	 * Get the 3 letter airport code
	 * 
	 * @return The 3 letter airport code
	 */
	public String code () {
		return mCode;
	}
	
	/**
	 * Set the latitude for the airport
	 * 
	 * @param latitude The north/south coordinate of the airport 
	 * @throws IllegalArgumentException is latitude is invalid
	 */
	public void latitude (double latitude) {
		if (isValidLatitude(latitude))
			mLatitude = latitude;
		else
			throw new IllegalArgumentException (Double.toString(latitude));
	}
	
	public void latitude (String latitude) {
		if (isValidLatitude(latitude))
			mLatitude = Double.parseDouble(latitude);
		else
			throw new IllegalArgumentException (latitude);
	}
	
	/**
	 * Get the latitude for the airport
	 * 
	 * @return The north/south coordinate of the airport 
	 */
	public double latitude () {
		return mLatitude;
	}
	
	/**
	 * Set the longitude for the airport
	 * 
	 * @param longitude The east/west coordinate of the airport
	 * @throws IllegalArgumentException is longitude is invalid
	 */
	public void longitude (double longitude) {
		if (isValidLongitude(longitude))
			mLongitude = longitude;
		else
			throw new IllegalArgumentException (Double.toString(longitude));
	}
	
	public void longitude (String longitude) {
		if (isValidLongitude(longitude))
			mLongitude = Double.parseDouble(longitude);
		else
			throw new IllegalArgumentException (longitude);
	}
	
	/**
	 * get the longitude for the airport
	 * 
	 * @return The east/west coordinate of the airport
	 */
	public double longitude () {
		return mLongitude;
	}

	/**
	 * Compare two airports based on 3 character code
	 * 
	 * This implementation delegates to the case insensitive version of string compareTo
	 * @return results of String.compareToIgnoreCase
	 */
	public int compareTo(Airport other) {
		return this.mCode.compareToIgnoreCase(other.mCode);
	}
	
	/**
	 * Compare two airports for sorting, ordering
	 * 
	 * Delegates to airport1.compareTo for ordering by 3 character code
	 * 
	 * @param airport1 the first airport for comparison
	 * @param airport2 the second / other airport for comparison
	 * @return -1 if airport ordered before airport2, +1 of airport1 after airport2, zero if no different in order
	 */
	public int compare(Airport airport1, Airport airport2) {
		return airport1.compareTo(airport2);
	}
	
	
	/**
	 * Determine if two airport objects are the same airport
	 * 
	 * Compare another object to this airport and return true if the other 
	 * object specifies the same airport as this object
	 * 
	 * @param obj is the object to compare against this object
	 * @return true if the param is the same airport as this, else false
	 */
	@Override
	public boolean equals (Object obj) {
		// every object is equal to itself
		if (obj == this)
			return true;
		
		// null not equal to anything
		if (obj == null)
			return false;
		
		// can't be equal if obj is not an instance of Airport
		if (!(obj instanceof Airport)) 
			return false;
		
		// if all fields are equal, the Airports are the same
		Airport rhs = (Airport) obj;
		if ((rhs.mName.equals(mName)) &&
				(rhs.mCode.equals(mCode)) &&
				(rhs.mLatitude == mLatitude) &&
				(rhs.mLongitude == mLongitude)) {
			return true;
		}
		
		return false;	
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
		
		// If the name isn't valid, the object isn't valid
		if ((mName == null) || (mName == ""))
			return false;
		
		// If we don't have a 3 character code, object isn't valid
		if ((mCode == null) || (mCode.length() != 3))
			return false;
		
		// Verify latitude and longitude are within range
		if ((mLatitude > Saps.MAX_LATITUDE) || (mLatitude < Saps.MIN_LATITUDE) ||
			(mLongitude > Saps.MAX_LONGITUDE) || (mLongitude < Saps.MIN_LONGITUDE)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Check for invalid 3 character airport code
	 * 
	 * @param code is the airport code to validate
	 * @return false if null or not 3 characters in length, else assume valid and return true
	 */
	public boolean isValidCode (String code) {
		// If we don't have a 3 character code it can't be valid valid
		if ((code == null) || (code.length() != 3))
			return false;
		return true;
	}
	
	/**
	 * Check for invalid airport name.
	 * 
	 * @param name is the name of the airport to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidName (String name) {
		// If the name is null or empty it can't be valid
		if ((name == null) || (name == ""))
			return false;
		return true;
	}
	
	/**
	 * Check if latitude is valid
	 * 
	 * @param latitude is the latitude to validate
	 * @return true if within valid range for latitude
	 */
	public boolean isValidLatitude (double latitude) {
		// Verify latitude is within valid range
		if ((latitude > Saps.MAX_LATITUDE) || (latitude < Saps.MIN_LATITUDE))
			return false;
		return true;
	}
	
	/**
	 * Check if latitude is valid.
	 * 
	 * @param latitude is the latitude to validate represented as a String
	 * @return true if within valid range for latitude
	 */
	public boolean isValidLatitude (String latitude) {
		double lat;
		try {
			lat = Double.parseDouble(latitude);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidLatitude (lat);
	}

	/**
	 * Check if longitude is valid
	 * 
	 * @param longitude is the longitude to validate
	 * @return true if within valid range for longitude
	 */
	public boolean isValidLongitude (double longitude) {
		// Verify longitude is within valid range
		if ((longitude > Saps.MAX_LONGITUDE) || (longitude < Saps.MIN_LONGITUDE))
			return false;
		return true;
	}
	
	/**
	 * Check if longitude is valid
	 * 
	 * @param longitude is the longitude to validate represented as a String
	 * @return true if within valid range for longitude
	 */
	public boolean isValidLongitude (String longitude) {
		double lon;
		try {
			lon = Double.parseDouble(longitude);
		} catch (NullPointerException | NumberFormatException ex) {
			return false;
		}
		return isValidLongitude (lon);
	}

}
