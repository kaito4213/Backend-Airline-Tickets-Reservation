package view;

import java.util.List;
import java.util.Scanner;
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
	private Boolean isSorting;
	
	/**
	 * constructor
	 * @param reservations list of matched reservations including outbound and inbound if any
	 * @param isRoundTrip if it is a round-way trip
	 */
	public SearchResultView(List<Reservations> reservations, Boolean isRoundTrip) {
		this.reservations = reservations;
		this.isRoundTrip = isRoundTrip;
		isSorting = false;
	}

	/**
	 * This method shows the result of search
	 */
	public void showSearchResult() {
		
		if (reservations.get(0).size() == 0) {
			System.out.println("***************no outbound flights for your current search***************");
		} else {
			System.out.println("***************search result of outbound flights***************");
			Reservations outboundList = reservations.get(0);
			for (Reservation outboundReservation : outboundList) {
				System.out.println(outboundReservation);
			}
		}
		
		if (isRoundTrip) {
			if (reservations.get(1).size() == 0) {
				System.out.println("***************no inbound flights for your current search***************");
			} else {
				System.out.println("***************search result of inbound flights***************");
				Reservations inboundList = reservations.get(1); 
				for (Reservation inboundReservation : inboundList) {
					System.out.println(inboundReservation);
				}
			}
		}
	}
	

	/**
	 * This method shows result of search after sorting
	 */
	public void showSortingResult() {
			Scanner scan = new Scanner(System.in);
			requestValidIsSorting(scan);
			
			if (!this.isSorting) return;
			
			if (reservations.get(0).size() == 0) {
				System.out.println("***************no outbound flights for your current search***************");
			} else {
				Reservations outboundList = reservations.get(0);
				requestValidSorter(scan, outboundList);
				System.out.println("***************search result of outbound flights***************");
				for (Reservation outboundReservation : outboundList) {
					System.out.println(outboundReservation);
				}
			}
			
			if (isRoundTrip) {
				if (reservations.get(1).size() == 0) {
					System.out.println("***************no inbound flights for your current search***************");
				} else {
					Reservations inboundList = reservations.get(1); 
					requestValidSorter(scan, inboundList);
					System.out.println("***************search result of inbound flights***************");
					for (Reservation inboundReservation : inboundList) {
						System.out.println(inboundReservation);
					}
				}
			}
		}
	
	/**
	 * set outbound reservation
	 * @return return index of selected reservation, 0 if customer didn't select any reservation
	 */
	public int setOutboundReservation() {
		
		String input;
		String confirmation;
		Scanner scanner = new Scanner(System.in);
		boolean validInput = true;
		
		do {
			System.out.println("***************Do you want to make reservation right now?(yes/no)***************");
			input = scanner.nextLine();
			
			if (input.toUpperCase().equals("YES")) {
				validInput = true;
				System.out.println("***************please select your OUTBOUND flights***************");
				input = scanner.nextLine();
				System.out.println("***************Are you sure to book this flight?***************");
				confirmation = scanner.nextLine();
				if (confirmation.toUpperCase().equals("YES")) {
					return Integer.parseInt(input);	
				} else {
					continue;
				}
			}
			else if (input.toUpperCase().equals("NO")) {
				validInput = false;
			}
		}while(validInput);
		
		return 0;
		
	}
	
	/**
	 * set inbound reservation
	 * @return return index of selected reservation, 0 if customer didn't select any reservation
	 */
	public int setInboundReservation() {
		String input;
		String confirmation;
		Scanner scanner = new Scanner(System.in);
		boolean validInput = true;
		
		do {
			System.out.println("***************Do you want to make reservation right now?(yes/no)***************");
			input = scanner.nextLine();
			
			if (input.toUpperCase().equals("YES")) {
				validInput = true;
				System.out.println("***************please select your INBOUND flights***************");
				input = scanner.nextLine();
				System.out.println("***************Are you sure to book this flight?***************");
				confirmation = scanner.nextLine();
				if (confirmation.toUpperCase().equals("YES")) {
					return Integer.parseInt(input);	
				} else {
					continue;
				}
			}
			else if (input.toUpperCase().equals("NO")) {
				validInput = false;
			}
		}while(validInput);
		
		return 0;
	}
	
	/**
	 * Request if needs sorting yes/no and set if valid
	 * @param scan customer input
	 */
	public void requestValidIsSorting(Scanner scan) {
		String input;
		boolean validSorting = false;
		do {
			System.out.println("***************Do you want to sort flights? (yes/no)***************");
			input = scan.nextLine();

			if (input.toUpperCase().equals("YES")) {
				isSorting = true;
				validSorting = true;
			} else if (input.toUpperCase().equals("NO"))
				validSorting = true;	 
		} while(!validSorting);
	}
	

	/**
	 * Request sorting strategy and set if valid
	 * @param scan customer input
	 * @param results reservations that need to be sorted
	 * 
	 */
	public void requestValidSorter(Scanner scan, Reservations results) {
		String input;
		boolean validSorting = false;
		do {
			System.out.println("***************Sort flights by price/time/departure/arrival***************");
			input = scan.nextLine();
			
			if (input.toUpperCase().equals("PRICE")) {
				results.sortbyTotalPrice(results);
				validSorting = true;
				
			} else if (input.toUpperCase().equals("TIME")) {
				results.sortbyTotalFlightTime(results);
				validSorting = true;
				
			} else if (input.toUpperCase().equals("DEPARTURE")) {
				results.sortbyDepartureTime(results);
				validSorting = true;
				
			} else if (input.toUpperCase().equals("Arrival")) {
				results.sortbyArrivalTime(results);
				validSorting = true;
			}				
		} while(!validSorting);
	}

}
