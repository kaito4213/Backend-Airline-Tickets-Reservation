/**
 * 
 */
package model.airport;

import java.util.ArrayList;

/**
 * This class aggregates a number of Airport. The aggregate is implemented as an ArrayList.
 * Airports can be added to the aggregate via XML strings in the format returned form the 
 * CS509 server, or as Airport objects using the ArrayList interface. Objects can 
 * be removed from the collection using the ArrayList interface.
 * 
  * @author blake
 *
 */
public class Airports extends ArrayList<Airport> {
	private static final long serialVersionUID = 1L;
	
    private static Airports mInstance;
    private ArrayList<String> list = null;

    public static Airports getInstance() {
        if(mInstance == null)
            mInstance = new Airports();

        return mInstance;
    }

    private Airports() {
      list = new ArrayList<String>();
    }
    // retrieve array from anywhere
    public ArrayList<String> getArray() {
     return this.list;
    }
    //Add element to array
    public void addToArray(String value) {
     list.add(value);
    }
		
}
