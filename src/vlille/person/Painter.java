package vlille.person;

import vlille.exceptions.VehicleAlreadyUsedException;
import vlille.vehicles.Vehicle;

public class Painter extends Professional {

    /* The time it takes the painter to paint a vehicle (in interval) */
    public static final int PAINTINGDURATION = 3;
    
    /* The current number of intervals used to paint the vehicle */
    public int paintingTime;
    
    /* The vehicle to be painted, null if no vehicle to paint */
    private Vehicle paintingVehicle;

    
    

    /** The constructor for Painter class */
    public Painter(int id) {
        super(id);
        this.paintingVehicle = null;
        this.paintingTime = 0;
    }

   
    
    
    /** ----------- Getter -------------*/
    
    /**
     * Get the painting time of this painter
     * @return int
     */
    public int getPaintingTime() {
    	return this.paintingTime;
    }
    
    
    /**
     * Get the vehicle this painter is currently painting
     * @return Vehicle
     */
    public Vehicle getPaintingVehicle() {
    	return this.paintingVehicle;
    }
    
    
    
    /** ----------- Other Functions ------------*/
    
    
    /**
     * Increase the painting time of this painter
     */
    public void increasePaintingTime() {
    	this.paintingTime++;
    }
    

    /**
     * Finish the painting and put back the vehicle in a station
     */
    private void finishPainting() {
    	super.finishAction(this.paintingVehicle);
	    this.paintingVehicle = null;
	    this.paintingTime = 0;
    }
    
    
    /** @Override */
    public void increaseTime() {
    	if (this.getPaintingVehicle() != null) {
    		
	    	this.increasePaintingTime();
	    	if (this.getPaintingTime() == PAINTINGDURATION)
	    		this.finishPainting();
    	}
    }
    
    /** 
     * Paint the vehicle given 
     * @param Vehicle the vehicle to be painted
     */
    public void paint(Vehicle vehicle) {
    	if (this.isAvailable()) {
	        this.paintingVehicle = vehicle;
	        this.paintingTime = 0;
	        this.available = false;
	        try {
	        	this.paintingVehicle.use();
	        } catch (VehicleAlreadyUsedException e) {
	        	System.out.println(e.getMessage());
	        }
	        System.out.println("The " + this +" is painting the "+vehicle);
    	}
    	else System.out.println("Error : the "+ this + " is not available.");
    }
    
    /** @Override */
    public String toString() {
    	return "painter "+this.getId();
    }
    
    

}
