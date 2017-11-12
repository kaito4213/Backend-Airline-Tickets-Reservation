package view;

import java.util.Scanner;

/**
 * This class will display the reservation you have made.
 *
 */
public class ReservationView {
	//private String searchResult;
	private String mReservation;
	
	public void makeReservation() {
		
		System.out.println("please confirm the reservation numnber: ");
		
		Scanner scan= new Scanner(System.in);
		String input = scan.nextLine();
		setReservation(input);
		scan.close();
	}
	
	public void showReservation(String reservation) {
		System.out.println("your reservation is: ");
		System.out.println(reservation);
	}
	
	public void setReservation(String reservation) {
		mReservation = reservation;
	}
}
