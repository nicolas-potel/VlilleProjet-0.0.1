package vlille.vehicles;

import vlille.exceptions.VehicleAlreadyUsedException;
import vlille.exceptions.VehicleNotUsedException;
import vlille.station.VehicleStation;
import vlille.vehicles.strategy.BikeStrategy;

public class Bike extends Vehicle {

    /* the number of intervals alone before the vehicle gets stolen */
	public static final int INTERVALSBEFORESTOLEN = 2;
	
	/* the number of intervals the vehicle has spent alone */
    protected int timeSpentAlone;


    /** Constructor for Bike class */
    public Bike(int id) {
        super(id);
        this.timeSpentAlone = 0;
        this.depositStrategy = new BikeStrategy(this);
    }
    
    /** Constructor for Bike class, which deposits the vehicle in a station */
    public Bike(int id, VehicleStation<Vehicle> station) {
    	super(id, station);
    	this.timeSpentAlone = 0;
        this.depositStrategy = new BikeStrategy(this);
    }



    /** --------Getter-------- */


    /**
     * return the time the vehicle spent alone
     * @return int
     */
    public int getTimeSpentAlone() {
        return this.timeSpentAlone;
    }



    /** --------Setter-------- */


    /** 
     * Set the time this bike has spent alone
     * 
     * @param int the time to set
    */
    public void setTimeSpentAlone(int time) {
        this.timeSpentAlone = time;
    }


    /** --------Other Functions-------- */

    /**
     * uses the vehicle and removes it from the station
     */
    @Override
    public void use() throws VehicleAlreadyUsedException {
        if (!this.getStolen()) {
        	super.use();
        	this.timeSpentAlone = 0;
        }
    }
    
    /**
     * Get the status of this bike in his station if it is alone or not
     * @return boolean
     */
    public boolean isAlone() {
    	if (this.getStation() != null) {
    	for (int i=0; i<this.station.getNbOfPlaces(); i++) {
    		Vehicle tmp = this.station.getVehicleAtPlace(i);
    		if (tmp != null && tmp!=null) return false;
    	}
    	return true;
    	} else return false;
    }
    
    /** @Override */
    public void putBack(VehicleStation<Vehicle> station) throws VehicleNotUsedException{
    	super.putBack(station);
    	this.depositStrategy.deposit();
    }
    
    /** @Override */
    public void increaseTime() {
    	super.increaseTime();
    	
    	if (this.isAlone()) {
    		this.timeSpentAlone++;
    		
    		if (this.timeSpentAlone == INTERVALSBEFORESTOLEN) {
    			this.setStolen(true);
    			System.out.println("The "+ this.toString() + " has been stolen.");
    		}
    	} else this.timeSpentAlone = 0;
    }
    
    /** @Override */
    public String toString() {
    	return "bike "+this.getId();
    }


}