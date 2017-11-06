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
	private SearchFlight search;			// new search
	private ReservationView reservation;
	
	public SearchController() {
		input = new InputView();
		input.setUserSearchInput();
		
		search = new SearchFlight();
		search.departureAirportCode(input.getDepartureAirportCode());
		search.arrivalAirportCode(input.getArrivalAirportCode());
		search.departureDate(input.getDepartureDate());
		search.seatPreference(input.getSeatPreference());
		search.stopOver(input.getHasStopOver());
		
		// search
		
		searchResult = new SearchResultView();
		searchResult.showSearchResult(search.toString());
	}
	
}