// Deprecated, use Dao.java instead

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

import model.flight.Flights;
import model.flight.Flight;

/**
 * @author blake
 *
 */
public class DaoFlight {
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
	 */
	public static Flights addAll (String xmlFlights) throws NullPointerException {
		Flights flights = new Flights();
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each airport to our collection
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
