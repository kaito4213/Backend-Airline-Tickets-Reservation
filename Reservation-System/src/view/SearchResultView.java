package view;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.flight.Flights;
import model.reservation.Reservation;
import model.reservation.Reservations;

/**
 * This class display the search result of all possible trips between departure airport 
 * and arrival airport.
 *
 */
public class SearchResultView {
	private List<Reservations> reservations;
	private Boolean isRoundTrip;
	
	public SearchResultView(List<Reservations> reservations, Boolean isRoundTrip) {
		this.reservations = reservations;
		this.isRoundTrip = isRoundTrip;
	}

	public void showSearchResult() {
	
		System.out.println("search result of outbound flights: \n");
		//------
		Reservations outboundList = reservations.get(0);
		outboundList.sortbyTotalPrice(outboundList);
		//------
		for (Reservation outboundReservation : outboundList) {
			System.out.println(outboundReservation);
		}
		
		if (isRoundTrip) {
			System.out.println("search result of inbound flights: \n");
			Reservations inboundList = reservations.get(1);
			for (Reservation inboundReservation : inboundList) {
				System.out.println(inboundReservation);
			}
		}
	}
	
	public int setReservation() {
		String input;
		Scanner scanner = new Scanner(System.in);
		System.out.println("please select your flights: ");
		input = scanner.nextLine();
		//scanner.close();
		return Integer.parseInt(input);
		
	}

}
