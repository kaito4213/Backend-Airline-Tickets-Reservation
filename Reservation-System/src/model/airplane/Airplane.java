package model.airplane;

public class Airplane {
	
	private int firstClassSeats;
	private int coachSeats;
	private String model;
	private String manufacturer;
        

	 /**
	 * default constructor
	 */
    public Airplane() {
        model = "";
        manufacturer = "";
        coachSeats = 0;
        firstClassSeats = 0;
    }
     
    /**
 	 * get the First Class seats
 	 * @return firstClassSeats
 	 */  
    public int getFirstClassSeats() {
		return firstClassSeats;
	}
    
    /**
     * get the Coach seats
  	 * @return coachSeats
     */
	public int getCoachSeats() {
		return coachSeats;
	}

	/**
	 * @return the model	     
	 */    
	public String getModel() {
		return model;
	}
	/**
	 * @return the manufacturer	     
	 */ 
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param newValue the firstClassSeats to set
	 */
    public void setFirstClassSeats(int newValue) {
    	firstClassSeats = newValue;
    }
    
	/**
	 * @param newValue the coachSeats to set
	 */
    public void setCoachSeats(int newValue) {
    	coachSeats = newValue;
    }
    
	/**
	 * @param newValue the Model to set
	 */
    public void setModel(String newValue) {
    	model = newValue;
    }
    
	/**
	 * @param newValue the manufacturer to set
	 */
    public void setManufacturer(String newValue) {
    	manufacturer = newValue;
    }
    
    /** 
     * print Airplane 
     */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(manufacturer).append(", ");
		sb.append(model).append(", ");
		sb.append("First Class Seats: ").append(firstClassSeats).append(", ");
		sb.append("Coach Seats: ").append(coachSeats);

		return sb.toString();
	}
	
	/**
	 * Determine if object instance has valid attribute data
	 * 
	 * Verifies the model has a value
	 * Verifies there are more than 0 first class seats
	 * Verifies there are more than 0 coach seats
	 * 
	 * @return true if object passes above validation checks
	 * 
	 */
	public boolean isValid() {
		
		// If the model is blank, the object isn't valid
		if (model == null )
			return false;
		
		// If we don't have first class seats or coach seats, object isn't valid
		if ((firstClassSeats == 0) && (coachSeats == 0))
			return false;
		
		return true;
	}
        
}