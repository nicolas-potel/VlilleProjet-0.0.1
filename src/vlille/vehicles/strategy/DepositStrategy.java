package vlille.vehicles.strategy;

import vlille.vehicles.Vehicle;

public abstract class DepositStrategy {
	
	protected Vehicle vehicle;

	/* Constructor of DepositStrategy class */
	public DepositStrategy(Vehicle v) {
		this.vehicle = v;
	}
	
    /**
     * The action to do when a vehicle is deposit
     */
    public void deposit() {
    	// does nothing
    }
    
}
