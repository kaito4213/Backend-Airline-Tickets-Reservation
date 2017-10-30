package controller;
import model.flight.Flights;
import model.search.*;
import view.*;

public class SearchController {
	
	private String mDepartureAirportCode;		// Three character code of the departure airport
	private String mArrivalAirportCode;			// Three character code of the arrival airport
	private String mDepartureDate;				// date of departure
	private String mSeatPreference;				// preference of seat class on the airplane
	private int mStopOver;						// number of stopOver from departure to arrival
	private SearchResultView searchResultView;
	private InputView inputView;
	private SearchFlight searchModel;					// new search
	
	public SearchController() {
		
		SearchFlight searchModel = new SearchFlight(mDepartureAirportCode, mArrivalAirportCode, mDepartureDate,
				mSeatPreference, mStopOver);
	}
	
	//check if the input attributes are valid
	private boolean isValid() {
		return true;
	}
	
	/***
	 * dest
	 * start
	 * 
	 * if bad dest -> 12345678 X wrong
	 * validate (dest, start)
	 * 
	 * m = new SearchModel(controller.start, controller.dest)
	 * 
	 * result = m.search();
	 * 
	 * 
	 * view = new SearchResultView(result)
	 * view.showResult();
	 * 
	 * return searchResults
	 * 
	 * 
	 */
	
}