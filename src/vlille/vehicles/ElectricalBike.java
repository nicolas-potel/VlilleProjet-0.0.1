package vlille.vehicles;

import vlille.exceptions.VehicleNotUsedException;
import vlille.station.VehicleStation;
import vlille.vehicles.strategy.ElectricalBikeStrategy;

public class ElectricalBike extends Bike {

    /* the change of the battery in percentage during one interval when used or charging */
    protected int chargeSpeed = 5;

    /* the current charge of the battery */
    protected int batteryCharge;
    
    /* true if the bike battery is charging */
    protected boolean charging;


    /** The constructor for ElectricalBike class */
    public ElectricalBike(int id) {
        super(id);
        this.batteryCharge = 100;
        this.depositStrategy = new ElectricalBikeStrategy(this);
    }
    
    /** The constructor for ElectricalBike class which deposits the vehicle in a station*/
    public ElectricalBike(int id, VehicleStation<Vehicle> station) {
    	super(id, station);
    	this.batteryCharge = 100;
        this.depositStrategy = new ElectricalBikeStrategy(this);
    }




    /** --------Getter-------- */


    /**
     * gets the current charge of the battery
     * @return int
     */
    public int getBatteryCharge() {
        return this.batteryCharge;
    }
    
    /**
     * returns the charge speed of the electrical bike
     * @return int
     */
    public int getChargeSpeed() {
    	return this.chargeSpeed;
    }

    /**
     * returns the charging boolean attribute of the electrical bike
     * @return boolean
     */
    public boolean getCharging() {
    	return this.charging;
    }

    /** --------Setter-------- */

    /**
     * sets the charging attribute to the specified boolean
     * @param boolean the attribute to be set
     */
    public void setCharging(boolean b) {
    	this.charging = b;
    }
    

    /** --------Other functions-------- */


    /** 
     * Discharge the battery of this electrical bike
     */
    public void dischargeBattery() {
    	if (this.batteryCharge >= this.chargeSpeed)
    		this.batteryCharge -= this.chargeSpeed;
    	else
    		this.batteryCharge = 0;
    }

    /** 
     * Charge the battery of this electrical bike
     */
    public void chargeBattery() {
    	if (this.batteryCharge >= 100 - this.chargeSpeed)
    		this.batteryCharge = 100;
    	else
    		this.batteryCharge += this.chargeSpeed;
    }
    
    
    /** @Override */
    public void putBack(VehicleStation<Vehicle> station) throws VehicleNotUsedException{
    	super.putBack(station);
    	this.depositStrategy.deposit();
    }
    
    /** @Override */
    public void increaseTime() {
    	super.increaseTime();
    	
    	if (this.charging) this.chargeBattery();
    	else if (this.used) this.dischargeBattery();
    }
    
    /** @Override */
    public String toString() {
    	return "electrical bike "+this.getId();
    }
    
}
