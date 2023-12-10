package vlille.vehicles;

import vlille.station.VehicleStation;

public class VehicleMock extends Vehicle {
	
    public VehicleMock(int id){
        super(id);
    }
    
    public VehicleMock(int id, VehicleStation<Vehicle> station) {
    	super(id, station);
    }
}