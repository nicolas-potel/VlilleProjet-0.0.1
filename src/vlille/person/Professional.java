package vlille.person;

import vlille.station.VehicleStation;
import vlille.vehicles.Vehicle;

import java.util.List;
import java.util.Random;

import vlille.controlcenter.ControlCenter;
import vlille.exceptions.VehicleNotUsedException;

public abstract class Professional extends Person {

    /** The constructor for Professional class */
    public Professional(int id) {
        super(id);
    }


    /** --------Getter-------- */


    /** ---------Setter--------- */
    
    /**
     * Finish the action the person is currently doing with the vehicle given
     * @param Vehicle the vehicle to finish to use
     */
    public void finishAction(Vehicle v) {
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
        	v.putBack(station);
        	this.setAvailability(true);
        	System.out.println("The " + this + " has finished working on the " +v + 
        			" and put it back in the station " + station.getId() + ".");
        		
	    } catch (VehicleNotUsedException e) {
	         System.out.println(e.getMessage());
	    }
    }

    
}
