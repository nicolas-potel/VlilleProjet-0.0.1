package vlille.person;
import vlille.controlcenter.ControlCenter;
import vlille.exceptions.VehicleAlreadyUsedException;
import vlille.exceptions.VehicleNotUsedException;
import vlille.station.VehicleStation;
import vlille.vehicles.Vehicle;

import java.util.List;
import java.util.Random;

public class Person {

    /* The id of this person */
    protected final int id;

    /* The vehicle of this person */
    protected Vehicle vehicle;
    
    /* Always true for a Person */
    protected boolean available;

    /** The constructor for Person class */
    public Person(int id) {
        this.id = id;
        this.available = true;
        this.vehicle = null;
    }


    /** --------Getter-------- */

    /** 
     * Get the id of this person
     * @return int
     */
    public int getId() {
        return this.id;
    }


    /**
     * Get the vehicle of this person
     * @return Vehicle
     */
    public Vehicle getVehicle() {
        return this.vehicle;
    }
    
    /**
     * Get the availability of this person
     * @return boolean
     */
    public boolean isAvailable() {
        return this.available;
    } 


    /** ---------Other functions--------- */
    
    /**
     * Set the availability of this person
     * @param boolean the availability to set
     */
    public void setAvailability(boolean available) {
        this.available = available;
    }

    /**
     * Give back the vehicle of this person
     */
    public void giveBackVehicle() {
        Random alea = new Random();
        VehicleStation<Vehicle> station = null;
        	
        List<VehicleStation<Vehicle>> stations = ControlCenter.getStations();
        int size = stations.size();
        int tempIndex;
        	
        while (station == null) {
        	tempIndex = alea.nextInt(size);
        	if (stations.get(tempIndex).getFirstAvailablePlace() != -1)
        		station = stations.get(tempIndex);
        }
        try {
        	this.vehicle.putBack(station);
        	System.out.println("The "+ this + " has put back the "+ this.vehicle + 
        			" in the station " + station.getId() + ".");
        	this.vehicle = null;
        		
	    } catch (VehicleNotUsedException e) {
	         System.out.println(e.getMessage());
	    }
	    
    }


    /**
     * Make the person take a vehicle
     * @param Vehicle the vehicle to take
     */
    public void takeVehicle(Vehicle vehicle) throws VehicleAlreadyUsedException{
        this.vehicle = vehicle;
        this.available = false;
        vehicle.use();
        System.out.println("The "+ this + " is now using the " + vehicle + ".");
    }
    
    /** @Override */
    public void increaseTime() {
    	if (!this.available) {
	    	Random alea = new Random();
	    	int n = alea.nextInt(2);
	    	if (n == 0) {
	    		this.giveBackVehicle();
	    		this.vehicle = null;
	    		this.available = true;
	    	}
    	}
    }
    
    /** @Override */
    public String toString() {
    	return "person " +this.getId();
    }

    
}
