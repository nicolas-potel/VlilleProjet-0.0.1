package vlille.station;

import vlille.exceptions.*;
import vlille.vehicles.Vehicle;

public class StationPlace<E extends Vehicle> {
    
    /* the station id where this station place is */
    private final int stationId;
    
    /* the id of this place */
    private final int placeId;

    /* the vehicle on this place */
    private E vehicle;


    /** The constructor for StationPlace class */
    public StationPlace(int stationId, int placeId) {
        this.stationId = stationId;
        this.placeId = placeId;
        this.vehicle = null;
    }


    /** --------Getter-------- */


    /**
     * Get the station id where this station place is
     * @return int
    */
    public int getStationId() {
        return this.stationId;
    }

    /** 
     * Get the id of this place
     * @return int
    */
    public int getPlaceId() {
        return this.placeId;
    }

    /**
     * Get the vehicle on this place
     * @return Vehicle
     */
    public E getVehicle() {
        return this.vehicle;
    }

    /**
     * Get the availability of this place
     * @return boolean
     */
    public boolean isAvailable() {
        return this.vehicle == null;
    }

    /** --------Setter-------- */


    /** --------Other functions-------- */


    /** 
     * Add a vehicle on this place
     * @param Vehicle the vehicle to add
     * @throws VehicleNotUsedException if the vehicle is already on a StationPlace
     */
    public void addVehicle(E vehicle) throws VehicleNotUsedException, NotEmptyPlaceException{
    	if (this.vehicle != null) throw new NotEmptyPlaceException("Error 'addVehicle': the vehicle cannot be added because the place "+this.placeId+" of the station "+this.stationId+" is not empty.");
        this.vehicle = vehicle;
    }


    /**
     * Remove the vehicle on this place
     * @return Vehicle
     */
    public E removeVehicle() throws EmptyPlaceException{
        if (this.vehicle == null) throw new EmptyPlaceException("Error 'removeVehicle': the place "+this.placeId+" of the station "+this.stationId +" is empty.");
        E vehicle = this.vehicle;
        this.vehicle = null;
        return vehicle;
    }
}
