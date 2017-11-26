package driver;

import java.text.ParseException;

import controller.SearchController;
import dao.ServerInterface;
<<<<<<< HEAD

=======
>>>>>>> 1553b9cfb2a216b29245bd3e6b3e4f3c41d03b8f


public class Driver {
	
	public static void main(String[] args) throws ParseException {
		/**
		if (args.length != 1) {
			System.err.println("usage: CS509.sample teamName");
			System.exit(-1);
			return;
		}
		*/
		
		// import Airports
		ServerInterface.INSTANCE.getAirports("Muse");
		
		// Try to get a list of airplanes
		ServerInterface.INSTANCE.getAirplanes("Muse");
		
		// Begin search
		new SearchController();
	}
}

/* To do:
 *       jUnit tests
 *       Sorting
 *
 * Nice to have
 *       GUI
 *       Performance
 */
