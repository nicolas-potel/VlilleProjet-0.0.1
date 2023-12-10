package vlille.person;
import vlille.vehicles.Vehicle;

import vlille.exceptions.*;

public class Mechanic extends Professional {

    /** The time it takes the mechanic to repair a vehicle (in intervals) */
    public static final int REPAIRDURATION = 2;
    
    /* The current number of intervals of repairing */
    public int repairingTime;
    
    /* The vehicle to be repaired, null if no repairing vehicle */
    public Vehicle repairingVehicle;

    /** The constructor for Mechanic class */
    public Mechanic(int id) {
        super(id);
        this.repairingTime = 0;
        this.repairingVehicle = null;
    }
    
    
    /** ------------- Getter ------------- */
    
    /**
     * Get the repairing time of this mechanic
     * @return int
     */
    public int getRepairingTime() {
    	return this.repairingTime;
    }
    
    /**
     * Get the vehicle currently getting repaired
     * @return Vehicle
     */
    public Vehicle getRepairingVehicle() {
    	return this.repairingVehicle;
    }
    
    
    /** ------------ Other Functions --------------- */
    
    
    /**
     * Increase the time this mechanic is having his vehicle to repair
     */
    public void increaseRepairingTime() {
    	this.repairingTime++;
    }
    

    /**
     * Repair the vehicle given
     * @param Vehicle the vehicle to repair
     */
    public void repair(Vehicle vehicle) {
    	if (this.isAvailable()){
	    	this.repairingTime = 0;
	        this.repairingVehicle = vehicle;
	        this.available = false;
	        try {
	        	this.repairingVehicle.use();
	        } catch (VehicleAlreadyUsedException e) {
	        	System.out.println(e.getMessage());
	        }
	        System.out.println("The "+this+" is repairing the "+vehicle);
    	}
    	else System.out.println("Error : the " + this + " is not available.");
    }
    
    /**
     * Finish the repairing of the vehicle and put it back in a station
     */
    protected void finishRepairing() {
    	super.finishAction(this.repairingVehicle);
	    this.repairingVehicle = null;
	    this.repairingTime = 0;
    }


    /** @Override */
    public void increaseTime() {
    	if (this.getRepairingVehicle() != null) {
    		
	    	this.increaseRepairingTime();
	    	if (this.getRepairingTime() == REPAIRDURATION)
	    		this.finishRepairing();
    	}
    }
    
    
    /** @Override */
    public String toString() {
    	return "mechanic "+this.getId();
    }
    
    

}
