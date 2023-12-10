package vlille.vehicles.strategy;
import vlille.vehicles.*;

public class ElectricalBikeStrategy extends DepositStrategy {


    /** The constructor for ElectricalBikeStrategy */
    public ElectricalBikeStrategy(Vehicle v) {
    	super(v);
    }

    /**
     * The action to do when a vehicle is deposit
     */
    public void deposit() {
    	// casting v to ElectricalBike before setting charging true
        ((ElectricalBike) this.vehicle).setCharging(true);
    }
    
}
