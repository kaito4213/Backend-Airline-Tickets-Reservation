package controller;

import model.search.SearchFlight;
import model.search.SearchFlights;
import view.InputView;
import view.SearchResultView;
import view.ReservationView;

/**
 * TODO: valid methods
 *
 */
public class SearchController {
	
	private InputView input;		//user interface which will get all the search information from user
	private SearchResultView searchResult;		//user interface which will display the search result to user	
	private SearchFlights search;			// new search
	private ReservationView reservation;
	
	public SearchController() {
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
		
		search.getSearchResult();
		
		//searchResult = new SearchResultView();
		//searchResult.showSearchResult(search.toString());
	}
	
}