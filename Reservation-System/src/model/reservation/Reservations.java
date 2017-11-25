package model.reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Reservations extends ArrayList<Reservation> {
	private static final long serialVersionUID = 1L;
	
	public void sortbyTotalPrice(Reservations reservations){
		
		 Comparator<Reservation> comparator = new Comparator<Reservation>(){
			 public int compare(Reservation r1, Reservation r2) {
				 
				 double price1 = r1.getTotalPrice();
				 double price2 = r2.getTotalPrice();
				 return price1 < price2 ? -1 : price1 == price2 ? 0 : 1;
				 }
			 }; 
		 
			 Collections.sort(reservations, comparator);
	}

	public void sortbyTotalFlightTime(Reservations reservations){
		
		Comparator<Reservation> comparator = new Comparator<Reservation>(){
			public int compare(Reservation r1, Reservation r2) {
				float t1 = r1.getTotalTime();
				float t2 = r2.getTotalTime();
				return t1 < t2 ? -1 : t1 == t2 ? 0 : 1;
				}
			};
			
			Collections.sort(reservations, comparator);
	}	     
	
	
	public void sortbyDepartureTime(Reservations  reservations){
		 
		Comparator<Reservation> comparator = new Comparator<Reservation>(){
			public int compare(Reservation r1, Reservation r2) {
				LocalDateTime t1 = r1.getDepartureAirportTime();
				LocalDateTime t2 = r2.getDepartureAirportTime();
				float diffInMinutes = java.time.Duration.between(t1, t2).toMinutes();
				return diffInMinutes > 0 ? -1 : t1 == t2 ? 0 : 1;
			}
		};
		
		Collections.sort(reservations, comparator);
	}
	
	public void sortbyArrivalTime(Reservations reservations){
		 
		Comparator<Reservation> comparator = new Comparator<Reservation>(){
			public int compare(Reservation r1, Reservation r2) {
				LocalDateTime t1 = r1.getArrivalAirportTime();
				LocalDateTime t2 = r2.getArrivalAirportTime();
				float diffInMinutes = java.time.Duration.between(t1, t2).toMinutes();
				return diffInMinutes > 0 ? -1 : t1 == t2 ? 0 : 1;
			}
		};
		
		Collections.sort(reservations, comparator);
	}
}