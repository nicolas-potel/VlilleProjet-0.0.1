package vlille.vehicles;
import java.awt.Color;
import java.util.*;
import vlille.exceptions.*;
import vlille.station.VehicleStation;
import vlille.vehicles.feature.*;
import vlille.vehicles.strategy.*;

public abstract class Vehicle {

    /* the id of this vehicle */
    protected int id;

    /* the number of uses of this vehicle*/
    protected int nbUses;
    
    /* the number of uses allowed before the vehicle needs a repair */
    public final int NBUSESBEFOREREPAIR = 3;
    
    /* the number of uses since the last repair */
    protected int nbOfUsesSinceLastRepair;

    /* the status showing if this vehicle is used or not */
    protected Boolean used;

    /* the status showing if this vehicle is stolen or not */
    protected Boolean stolen;

    /* the color of this vehicle */
    protected Color color;

    /* the station where this vehicle is, if null, the vehicle is used*/
    protected VehicleStation<Vehicle> station;

    /* the different features of this vehicle */
    protected ArrayList<Feature> features;

    /* the action to do when this vehicle is deposit */
    protected DepositStrategy depositStrategy;


    /** The constructor for Vehicle class */
    public Vehicle(int id) {
        this.id = id;
        this.used = false;
        this.stolen = false;
        this.nbUses = 0;
        this.features = new ArrayList<Feature>();
        this.nbOfUsesSinceLastRepair = 0;
        this.station = null;
    }

    /** Constructor for Vehicle class, which deposits the vehicle in a station */
    public Vehicle(int id, VehicleStation<Vehicle> station) {
        this.id = id;
        this.used = false;
        this.stolen = false;
        this.nbUses = 0;
        this.features = new ArrayList<Feature>();
        this.station = station;
        try {
        	int index = station.getFirstAvailablePlace();
        	if (index > -1) {
        		station.addVehicleAtPlace(index, this);
        	}
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    	
    }



    /** -------- Getter -------- */

    /** 
     * Get the id of this vehicle
     * @return int
     */
    public int getId() {
        return this.id;
    }   

    /** 
     * Get the number of uses of this vehicle 
     * @return int
    */
    public int getNbUses() {
        return this.nbUses;
    }

    /** 
     * Get the status of this vehicle if it's used or not
     * @return boolean
     */
    public Boolean getUsed() {
        return this.used;
    }

    /**
     * Get the status of this vehicle if it's stolen or not 
     * @return boolean
    */
    public Boolean getStolen() { 
        return this.stolen;
    }

    /** 
     * Get the color of this vehicle 
     * @return Color
    */
    public Color getColor() {
        return this.color;
    }

    /** 
     * Get the station id where this vehicle is
     * If the return is null, then the vehicle is not in a station (and is used)
     * @return VehicleStation<Vehicle>
     */
    public VehicleStation<Vehicle> getStation() {
        return this.station;
    }

    /** 
     * Get the features list of this vehicle
     * @return ArrayList<Feature>
     */
    public ArrayList<Feature> getFeatures() {
        return this.features;
    }

    /**
     * Get the deposit strategy of this vehicle
     * @return DepositStrategy
     */
    public DepositStrategy getStrategy() {
        return this.depositStrategy;
    }
    
    /**
     * Get the number of uses since the last repair
     * @return int
     */
    public int getNbOfUsesSinceLastRepair() {
    	return this.nbOfUsesSinceLastRepair;
    }



    /** ---------Setter--------- */

    /** 
     * Set the color for this vehicle 
     * 
     * @param Color the color to set
    */
    public void setColor(Color color) {
        this.color = color;
    }

    /** 
     * Set the status stolen 
     *
     * @param boolean the status to set  
    */
    public void setStolen(Boolean stolen) {
        this.stolen = stolen;
    }

    /**
     * Set the deposit strategy
     * 
     * @param DepositStrategy the deposit strategy to set
     */
    public void setStrategy(DepositStrategy strategy) {
        this.depositStrategy = strategy;
    }

    /**
     * Set the number of uses of this vehicle at 0
     */
    public void setNbUsesToZero() {
        this.nbUses = 0;
    }
    
    /**
     * Set the number of uses since the last repair at 0
     */
    public void setNbOfUsesSinceLastRepairToZero() {
        this.nbOfUsesSinceLastRepair = 0;
    }


    /** ---------Other functions--------- */


    /** 
     * Use this vehicle
     * @throws VehicleAlreadyUsedException if the vehicle is already used
    */
    public void use() throws VehicleAlreadyUsedException{
        if (this.used == true) throw new VehicleAlreadyUsedException("Error: this vehicle is already used.");
        if (this.station != null) {
	        int index = this.station.getIndexOfVehicle(this);
	        if (index != -1) {
	        	try {
	        		this.station.removeVehicleAtPlace(index);
	        		this.station = null;
	    	        this.used = true;
	    	        this.nbOfUsesSinceLastRepair++;
	    	        this.nbUses++;
	        	} catch (EmptyPlaceException e) {
	        		System.out.println(e.getMessage());
	        	}
	        } 
        }
        
    }

    /**
     * Put back this vehicle in a station
     *
     * @param VehicleStation<Vehicle> the station where to put back
     * @throws VehicleNotUsedException if the vehicle is not already used
    */
    public void putBack(VehicleStation<Vehicle> station) throws VehicleNotUsedException{
        if (!this.used) throw new VehicleNotUsedException("Error: the "+ this.toString() +" is not used.");
        this.used = false;
        this.station = station;
        try {
        	int index = station.getFirstAvailablePlace();
        	if (index > -1) {
        		station.addVehicleAtPlace(index, this);
        	}
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }

    /**
     * Add a feature to this vehicle
     *
     * @param Feature the feature to add
    */
    public void addFeature(Feature feature) {
        this.features.add(feature);
    } 


    /**
     * Print the features of this vehicle
     */
    public String printFeatures() {
    	String res = "";
    	List<Feature> features = this.getFeatures();
        for (int i=0; i<features.size(); i++) 
            if (i == 0)
            	res += features.get(i);
            else
            	res += ", " + features.get(i);
        return res;
    }
    
    /*
     * Increase the time for this vehicle
     */
    public void increaseTime() {
    	// Nothing
    }

}