package driver;

import java.text.ParseException;
import java.util.Scanner;

import controller.SearchController;
import dao.ServerInterface;
import model.airport.Airport;



public class Driver {
	
	public static void main(String[] args) throws ParseException {
		
		// import Airports
		ServerInterface.INSTANCE.getAirports("Muse");
		
		// Try to get a list of airplanes
		ServerInterface.INSTANCE.getAirplanes("Muse");
		
		// Begin search
		String input;
		Scanner scan = new Scanner(System.in);
		boolean validInput = false;
		
		do {
			System.out.println("***************Do you want to start a new search?(yes/no)***************");
			input = scan.nextLine();
			
			if (input.toUpperCase().equals("YES")) {
				validInput = true;
				new SearchController();
			}
			else if (input.toUpperCase().equals("NO")) {
				System.out.println("**********Thanks for your visiting!***********");
				System.exit(-1);
			}
		}while(validInput);
		
	}
}

/* To do:
 *       jUnit tests
 *
 * Nice to have
 *       GUI
 *       Performance
 */
