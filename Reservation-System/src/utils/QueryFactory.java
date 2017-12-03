/**
 * 
 */
package utils;
import model.reservation.Reservation;
/**
 * @author blake
 * @version 1.2
 *
 */
public class QueryFactory {
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airports
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getAirports(String teamName) {
		return "?team=" + teamName + "&action=list&list_type=airports";
	}
	
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airports
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getFlights(String teamName) {
		return "?team=" + teamName + "&action=list&list_type=departing&airport=BOS&day=2017_12_10";
	}
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airports
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @param Departureairport three unique code of departure airport
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getFlights(String teamName, String Departureairport) {
		return "?team=" + teamName + "&action=list&list_type=departing&airport=" + Departureairport;
	}
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airports
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @param Departureairport three unique code of departure airport
	 * @param date departure date in "mm_dd_yyyy" format
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getFlights(String teamName, String Departureairport, String date) {
		return "?team=" + teamName + "&action=list&list_type=departing&airport=" + Departureairport 
				+ "&day=" + date;
	}
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airplanes
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getAirplanes(String teamName) {
		return "?team=" + teamName + "&action=list&list_type=airplanes" + 
				"";
	}
	
	/**
	 * Get timezone per lat/long
	 * 
	 * @param latitude is the latitude of the airport
	 * @param longitude is the longitude of the airport
	 * @return the String written to HTTP POST to lock server database 
	 */
	public static String getTimeZone(double latitude, double longitude) {
		return "https://maps.googleapis.com/maps/api/timezone/xml?location=" + latitude + "," + 
	           longitude + "&timestamp=1331161200&key=AIzaSyAA4hXXyr4fzABw0Kj66PcsCU5uAGkc2Fw";
	}
	
	/**
	 * Lock the server database so updates can be written
	 * 
	 * @param teamName is the name of the team to acquire the lock
	 * @return the String written to HTTP POST to lock server database 
	 */
	public static String lock (String teamName) {
		return "team=" + teamName + "&action=lockDB";
	}
	
	/**
	 * Unlock the server database after updates are written
	 * 
	 * @param teamName is the name of the team holding the lock
	 * @return the String written to the HTTP POST to unlock server database
	 */
	public static String unlock (String teamName) {
		return "team=" + teamName + "&action=unlockDB";
	}


	/**
	 * Update the server database with flights to be updated
	 * 
	 * @param teamName is the name of the team holding the lock
	 * @param reservation The reservation that user chooses
	 * @return the String written to the HTTP POST to update flights on server
	 */
	public static String updateFlights (String teamName, Reservation reservation) {
		return "team=" + teamName + "&action=buyTickets&flightData=" + reservation.toXML();
	}

}