package model.airplane;

public class Airplane {
	
	private int firstClassSeats;
	private int coachSeats;
	private String model;
	private String manufacturer;
        
    // the Airplane class has
    // one constructor
    public Airplane() {
        model = "";
        manufacturer = "";
        coachSeats = 0;
        firstClassSeats = 0;
    }
        
    // Airplane Class Methods
    public void setFirstClassSeats(int newValue) {
    	firstClassSeats = newValue;
    }
    public void setCoachSeats(int newValue) {
    	coachSeats = newValue;
    }
    public void setModel(String newValue) {
    	model = newValue;
    }
    public void setManufacturer(String newValue) {
    	manufacturer = newValue;
    }
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
