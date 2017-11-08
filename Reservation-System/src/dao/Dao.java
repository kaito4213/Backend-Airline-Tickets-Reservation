/**
 * 
 */
package dao;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import model.airplane.Airplane;
import model.airplane.Airplanes;
import model.airport.Airport;
import model.airport.Airports;
import model.flight.Flight;
import model.flight.Flights;

/**
 * @author blake
 *
 */
public class Dao {
	/**
	 * Builds collection of airplane from airplanes described in XML
	 * 
	 * Parses an XML string to read each of the airplanes and adds each valid airplane 
	 * to the collection. The method uses Java DOM (Document Object Model) to convert
	 * from XML to Java primitives. 
	 * 
	 * Method iterates over the set of Airplane nodes in the XML string and builds
	 * an Airplane object from the XML node string and add the Airplane object instance to
	 * the Airplanes collection.
	 * 
	 * @param xmlAirplanes XML string containing set of airplanes 
	 * @return [possibly empty] collection of Airplanes in the xml string
	 * @throws NullPointerException included to keep signature consistent with other addAll methods
	 * 
	 * @pre the xmlAirplanes string adheres to the format specified by the server API
	 * @post the [possibly empty] set of Airports in the XML string are added to collection
	 */
	public static Airplanes addAllAirplanes (String xmlAirplanes) throws NullPointerException {
		Airplanes airplanes = new Airplanes();
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each airplane to our collection
		Document docAirplanes = buildDomDoc (xmlAirplanes);
		NodeList nodesAirplanes = docAirplanes.getElementsByTagName("Airplane");
		
		for (int i = 0; i < nodesAirplanes.getLength(); i++) {
			Element elementAirplane = (Element) nodesAirplanes.item(i);
			Airplane airplane = buildAirplane (elementAirplane);
			
			if (airplane.isValid()) {
				airplanes.add(airplane);
			}
		}
		
		return airplanes;
	}

	/**
	 * Creates an Airplane object from a DOM node
	 * 
	 * Processes a DOM Node that describes an Airplane and creates an Airplane object from the information
	 * @param nodeAirplane is a DOM Node describing an Airplane
	 * @return Airplane object created from the DOM Node representation of the Airplane
	 * 
	 * @pre nodeAirplane is of format specified by CS509 server API
	 */
	static private Airplane buildAirplane (Node nodeAirplane) {
		/**
		 * Instantiate an empty Airplane object
		 */
		Airplane airplane = new Airplane();

		String model;
		String manufacturer;
		int firstClassSeats;
		int coachSeats;
		
		// The airplane element has attributes of model and manufacturer
		Element elementAirplane = (Element) nodeAirplane;
		model = elementAirplane.getAttributeNode("Model").getValue();
		manufacturer = elementAirplane.getAttributeNode("Manufacturer").getValue();
		
		// The coach seats and first class seats are child elements
		Element elementCoachFistClass;
		elementCoachFistClass = (Element)elementAirplane.getElementsByTagName("FirstClassSeats").item(0);
		firstClassSeats = Integer.parseInt(getCharacterDataFromElement(elementCoachFistClass));
		
		elementCoachFistClass = (Element)elementAirplane.getElementsByTagName("CoachSeats").item(0);
		coachSeats = Integer.parseInt(getCharacterDataFromElement(elementCoachFistClass));

		/**
		 * Update the Airplane object with values from XML node
		 */
		airplane.setModel(model);
		airplane.setManufacturer(manufacturer);
		airplane.setFirstClassSeats(firstClassSeats);
		airplane.setCoachSeats(coachSeats);		
		
		return airplane;
	}
	
	/**
	 * Builds collection of flights from flights described in XML
	 * 
	 * Parses an XML string to read each of the flights and adds each valid flight 
	 * to the collection. The method uses Java DOM (Document Object Model) to convert
	 * from XML to Java primitives. 
	 * 
	 * Method iterates over the set of Flight nodes in the XML string and builds
	 * a Flight object from the XML node string and add the Flight object instance to
	 * the Flights collection.
	 * 
	 * @param xmlFlights XML string containing set of flights 
	 * @return [possibly empty] collection of Flights in the xml string
	 * @throws NullPointerException included to keep signature consistent with other addAll methods
	 * 
	 * @pre the xmlFlights string adheres to the format specified by the server API
	 * @post the [possibly empty] set of Airports in the XML string are added to collection
	 */
	public static Flights addAllFlights (String xmlFlights) throws NullPointerException {
		Flights flights = new Flights();
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each flight to our collection
		Document docFlights = buildDomDoc (xmlFlights);
		NodeList nodesFlights = docFlights.getElementsByTagName("Flight");
		
		for (int i = 0; i < nodesFlights.getLength(); i++) {
			Element elementFlight = (Element) nodesFlights.item(i);
			Flight flight = buildFlight (elementFlight);
			
			if (flight.isValid()) {
				flights.add(flight);
			}
		}
		
		return flights;
	}

	/**
	 * Creates a flight object from a DOM node
	 * 
	 * Processes a DOM Node that describes a Flight and creates a Flight object from the information
	 * @param nodeFlight is a DOM Node describing a Flight
	 * @return Flight object created from the DOM Node representation of the Flight
	 * 
	 * @pre nodeFlight is of format specified by CS509 server API
	 */
	static private Flight buildFlight (Node nodeFlight) {
		/**
		 * Instantiate an empty flight object
		 */
		Flight flight = new Flight();

		int number, flightTime, firstClassBooked, coachBooked;
		String airplane;
		String departureAirport;
		String departureAirportTime;
		String arrivalAirport;
		String arrivalAirportTime;
		String firstClassPrice;
		String coachPrice;
		
		// The flight element has attributes including number
		Element elementFlight = (Element) nodeFlight;
		number = Integer.parseInt(elementFlight.getAttributeNode("Number").getValue(),10);
		airplane = elementFlight.getAttributeNode("Airplane").getValue();
		flightTime = Integer.parseInt(elementFlight.getAttributeNode("FlightTime").getValue(),10);
		
		// The arrival and departure airports/time are child elements
		Element elementDep, elementDepCode,elementDepTime;
		elementDep = (Element)elementFlight.getElementsByTagName("Departure").item(0);
		elementDepCode = (Element)elementDep.getElementsByTagName("Code").item(0);
		departureAirport = getCharacterDataFromElement(elementDepCode);
		elementDepTime = (Element)elementDep.getElementsByTagName("Time").item(0);
		departureAirportTime = getCharacterDataFromElement(elementDepTime);

		Element elementArr, elementArrCode,elementArrTime;
		elementArr = (Element)elementFlight.getElementsByTagName("Arrival").item(0);
		elementArrCode = (Element)elementArr.getElementsByTagName("Code").item(0);
		arrivalAirport = getCharacterDataFromElement(elementArrCode);
		elementArrTime = (Element)elementArr.getElementsByTagName("Time").item(0);
		arrivalAirportTime = getCharacterDataFromElement(elementArrTime);
		
		// Coach and first class price and tickets booked
		Element elementSeating, elementFirstClass, elementCoach;
		elementSeating = (Element)elementFlight.getElementsByTagName("Seating").item(0);
		elementFirstClass = (Element)elementSeating.getElementsByTagName("FirstClass").item(0);
		firstClassBooked = Integer.parseInt(getCharacterDataFromElement(elementFirstClass));
		firstClassPrice = elementFirstClass.getAttributeNode("Price").getValue();
		elementCoach = (Element)elementSeating.getElementsByTagName("Coach").item(0);
		coachBooked = Integer.parseInt(getCharacterDataFromElement(elementCoach));
		coachPrice = elementCoach.getAttributeNode("Price").getValue();
		


		/**
		 * Update the Airport object with values from XML node
		 */
		flight.setNumber(number);
		flight.setAirplane(airplane);
		flight.setFlightTime(flightTime);
		flight.setDepartureAirport(departureAirport);
		flight.setDepartureTime(departureAirportTime);
		flight.setArrivalAirport(arrivalAirport);
		flight.setArrivalTime(arrivalAirportTime);
		flight.setFirstClassBooked(firstClassBooked);
		flight.setFirstClassPrice(firstClassPrice);
		flight.setCoachBooked(coachBooked);
		flight.setCoachPrice(coachPrice);
		
		return flight;
	}
	/**
	 * Builds collection of airports from airports described in XML
	 * 
	 * Parses an XML string to read each of the airports and adds each valid airport 
	 * to the collection. The method uses Java DOM (Document Object Model) to convert
	 * from XML to Java primitives. 
	 * 
	 * Method iterates over the set of Airport nodes in the XML string and builds
	 * an Airport object from the XML node string and add the Airport object instance to
	 * the Airports collection.
	 * 
	 * @param xmlAirports XML string containing set of airports 
	 * @return [possibly empty] collection of Airports in the xml string
	 * @throws NullPointerException included to keep signature consistent with other addAll methods
	 * 
	 * @pre the xmlAirports string adheres to the format specified by the server API
	 * @post the [possibly empty] set of Airports in the XML string are added to collection
	 */
	public static Airports addAllAirports (String xmlAirports) throws NullPointerException {
		Airports airports = new Airports();
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each airport to our collection
		Document docAirports = buildDomDoc (xmlAirports);
		NodeList nodesAirports = docAirports.getElementsByTagName("Airport");
		
		for (int i = 0; i < nodesAirports.getLength(); i++) {
			Element elementAirport = (Element) nodesAirports.item(i);
			Airport airport = buildAirport (elementAirport);
			
			if (airport.isValid()) {
				airports.add(airport);
			}
		}
		
		return airports;
	}

	/**
	 * Creates an Airport object from a DOM node
	 * 
	 * Processes a DOM Node that describes an Airport and creates an Airport object from the information
	 * @param nodeAirport is a DOM Node describing an Airport
	 * @return Airport object created from the DOM Node representation of the Airport
	 * 
	 * @pre nodeAirport is of format specified by CS509 server API
	 */
	static private Airport buildAirport (Node nodeAirport) {
		/**
		 * Instantiate an empty Airport object
		 */
		Airport airport = new Airport();

		String name;
		String code;
		double latitude;
		double longitude;
		
		// The airport element has attributes of Name and 3 character airport code
		Element elementAirport = (Element) nodeAirport;
		name = elementAirport.getAttributeNode("Name").getValue();
		code = elementAirport.getAttributeNode("Code").getValue();
		
		// The latitude and longitude are child elements
		Element elementLatLng;
		elementLatLng = (Element)elementAirport.getElementsByTagName("Latitude").item(0);
		latitude = Double.parseDouble(getCharacterDataFromElement(elementLatLng));
		
		elementLatLng = (Element)elementAirport.getElementsByTagName("Longitude").item(0);
		longitude = Double.parseDouble(getCharacterDataFromElement(elementLatLng));

		/**
		 * Update the Airport object with values from XML node
		 */
		airport.name(name);
		airport.code(code);
		airport.latitude(latitude);
		airport.longitude(longitude);
		
		return airport;
	}

	/**
	 * Adds airport Time Zone based upon xml string returned
	 * 
	 * Parses the XML file and updates an airport
	 * 
	 * @param xmlAirportTimeZone XML String containing set of objects
	 * @param airport an instance of Airports
	 * @return void
	 */
	public static void addAirportTimeZone(String xmlAirportTimeZone, Airport airport) throws NullPointerException {
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each airport to our collection
		Document docAirportTimeZone = buildDomDoc (xmlAirportTimeZone);
		NodeList nodesTimeZone = docAirportTimeZone.getElementsByTagName("TimeZoneResponse");
		Element elementAirportTimeZone = (Element) nodesTimeZone.item(0);
		
		Element elementTimeZoneName = (Element) elementAirportTimeZone.getElementsByTagName("time_zone_id").item(0);
		String timeZone = getCharacterDataFromElement(elementTimeZoneName);
		airport.timeZone(timeZone);
	}

	/**
	 * Builds a DOM tree from an XML string
	 * 
	 * Parses the XML file and returns a DOM tree that can be processed
	 * 
	 * @param xmlString XML String containing set of objects
	 * @return DOM tree from parsed XML or null if exception is caught
	 */
	static private Document buildDomDoc (String xmlString) {
		/**
		 * load the xml string into a DOM document and return the Document
		 */
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmlString));
			
			return docBuilder.parse(inputSource);
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		catch (SAXException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Retrieve character data from an element if it exists
	 * 
	 * @param e is the DOM Element to retrieve character data from
	 * @return the character data as String [possibly empty String]
	 */
	private static String getCharacterDataFromElement (Element e) {
		Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	        CharacterData cd = (CharacterData) child;
	        return cd.getData();
	      }
	      return "";
	}
}