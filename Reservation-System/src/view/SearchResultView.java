package view;

import java.util.Scanner;
/**
 * This class display the search result of all possible trips between departure airport 
 * and arrival airport.
 *
 */
public class SearchResultView {
	//private String searchResult;
	private String mReservation;
	
	public void showSearchResult(String searchResult) {
		System.out.println("The result of search: ");
		System.out.println(searchResult);
		
		System.out.println("please confirm the reservation numnber: ");
		
		Scanner scan= new Scanner(System.in);
		String input = scan.nextLine();
		setReservation(input);
		scan.close();
		
	}
	
	
	public void setReservation(String reservation) {
		mReservation = reservation;
	}
}
