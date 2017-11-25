package controller;

import java.text.ParseException;
import java.util.List;

import model.flight.Flights;
import model.reservation.Reservation;
import model.reservation.Reservations;
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
			System.out.println("no flights available");
			return;
		}
		
		searchResult = new SearchResultView(result, search.roundTrip());
		searchResult.showSearchResult();
		
		int outboundReservationIndex = searchResult.setReservation();
		for (Reservation reservation : result.get(0)) {
			if (outboundReservationIndex == reservation.getIndex()) {
				reservation.confirmReservation();
			}
		}
		
		if (search.roundTrip() && result.size() > 1) {
			int inboundReservationIndex = searchResult.setReservation();
			for (Reservation reservation : result.get(0)) {
				if (inboundReservationIndex == reservation.getIndex()) {
					reservation.confirmReservation();
				}
			}
		} 
		
	}	
}