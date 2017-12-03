package controller;

import java.text.ParseException;
import java.util.List;

import model.reservation.Reservation;
import model.reservation.Reservations;
import model.search.SearchFlights;
import view.InputView;
import view.SearchResultView;

/**
 * This class will take control of data flow. User will enter the search information in view layer,
 * controller will pass the input to model, and model will connect server and search the result.
 * The matched flights will be passed to result displayer in view package.
 *
 */
public class SearchController {
	
	private InputView input;		//user interface which will get all the search information from user
	private SearchResultView searchResult;		//user interface which will display the search result to user	
	private SearchFlights search;			// new search
	
	/**
	 * Default constructor
	 * Constructor without parameters. 
	 * Set user input, start a search, and allow making reservations.
	 * 
	 * @throws ParseException if the date parsing fails
	 */
	public SearchController() throws ParseException {
		input = new InputView();
		input.setUserSearchInput();
		
		search = new SearchFlights();
		search.departureAirportCode(input.getDepartureAirportCode());
		search.arrivalAirportCode(input.getArrivalAirportCode());
		search.departureDate(input.getDepartureDate());
		search.returnDate(input.getReturnDate());
		search.seatPreference(input.getSeatPreference());
		search.stopOver(input.getHasStopOver());
		search.roundTrip(input.getIsRoundTrip());
		
		List<Reservations> result = search.getSearchResult();
		
		if (result == null || result.size() == 0) {
			System.out.println("no flights available for your search");
			return;
		}
		
		searchResult = new SearchResultView(result, search.roundTrip());
		searchResult.showSearchResult();
		searchResult.showSortingResult();
		
		int outboundReservationIndex = searchResult.setOutboundReservation();
		for (Reservation reservation : result.get(0)) {
			if (outboundReservationIndex == reservation.getIndex()) {
				reservation.confirmReservation();
			}
		}

		if (search.roundTrip() && result.size() > 1) {
			int inboundReservationIndex = searchResult.setInboundReservation();
			for (Reservation reservation : result.get(0)) {
				if (inboundReservationIndex == reservation.getIndex()) {
					reservation.confirmReservation();
				}
			}			
		}
	}	
}