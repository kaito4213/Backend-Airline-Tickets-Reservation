package view;

import java.util.Scanner;

/**
 * This class will display the reservation you have made.
 *
 */
public class ReservationView {
	private String mReservation;
	
	/** 
	 * This method confirms if customer want to make reservation
	 */
	public void makeReservation() {
		
		System.out.println("***************please confirm the reservation number***************");
		
		Scanner scan= new Scanner(System.in);
		String input = scan.nextLine();
		setReservation(input);
		scan.close();
	}
	/**
	 * This method shows customer's reservation information
	 * @param reservation selected by customer
	 */
	public void showReservation(String reservation) {
		System.out.println("***************your reservation is***************");
		System.out.println(reservation);
	}
	
	/**
	 * set reservation
	 * @param reservation selected by customer
	 */
	public void setReservation(String reservation) {
		mReservation = reservation;
	}
}
