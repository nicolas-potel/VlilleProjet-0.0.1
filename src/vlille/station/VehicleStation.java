package vlille.station;
import java.util.*;

import vlille.exceptions.*;
import vlille.vehicles.*;

public class VehicleStation<E extends Vehicle> {

	/* The constraints of number of places for all vehicle stations */
    public static final int PLACESLOWERLIMIT = 10;
    public static final int PLACESUPPERLIMIT = 20;

    /* The vehicles in this station */
    private List<StationPlace<E>> vehicles;
    
    /* The id of the station */
    private final int id;
    
    /* The number of places of this station */
    private int nbPlaces;
    
    /* the amount of intervals the station got empty
     get reseted if a vehicle is placed in the station */
    private int timeSpentEmpty;

    /**
     * constructor of abstract class VehicleStation
     * @param int the id of the station
     * @param int the number of places of the station
     */
    public VehicleStation (int id, int nbP){
        if (nbP < VehicleStation.PLACESLOWERLIMIT 
                || nbP > VehicleStation.PLACESUPPERLIMIT){
            System.out.println("Error: the number of places is incorrect.");
            System.exit(1);
        }
        this.id=id;
        this.nbPlaces = nbP;
        this.vehicles = new ArrayList<StationPlace<E>>();
        for (int i=0; i<nbP; i++) {
            this.vehicles.add(new StationPlace<E>(this.id, i));
        }
        this.timeSpentEmpty = 0;
    }

    /**
     * getting the nb of places of the station
     * @return int
     */
    public int getNbOfPlaces(){
        return this.nbPlaces;
    }

    /**
     * getting the id of the station
     * @return int
     */
    public int getId(){
        return this.id;
    }

    /**
     * getting true if the specified place is available
     * @param int the number of the place to check
     * @return boolean
     */
    public boolean isPlaceAvailable(int id){
        return this.vehicles.get(id).isAvailable();
    }

    /**
     * getting the vehicle parked at the specified place
     * returns null if the place is empty
     * @param int the number of the concerned place
     * @return Vehicle
     */
    public E getVehicleAtPlace(int id){
        return this.vehicles.get(id).getVehicle();
    }
    

    /**
     * removes the vehicle at the specified place and returns it
     * return null if the place is empty
     * @param int the index of concerned place
     * @return Vehicle
     * @throws EmptyPlaceException if the StationPlace is empty
     */
    public E removeVehicleAtPlace(int id) throws EmptyPlaceException{
        return this.vehicles.get(id).removeVehicle();
    }
    
    
    /**
     * return the index of the place where is a given vehicle 
     * return -1 if the vehicle is not in the station
     * @param Vehicle the vehicle to search in the station
     * @return int
     */
    public int getIndexOfVehicle(E vehicle) {
    	for (StationPlace<E> sp : this.vehicles) {
    		E v = sp.getVehicle();
    		if (v != null && v.equals(vehicle)) {
    			return sp.getPlaceId();
    		}
    	}
    	return -1;
    }
    

    /**
     * adds the specified vehicle to the specified place if possible
     * @param int the index of the place
     * @param Vehicle the vehicle to be added
     * @throws NotEmptyPlaceException if the place is not empty
     * @throws VehicleNotUsedException if the vehicle is already on a station place
     */
    public void addVehicleAtPlace(int i, E vehicle) throws NotEmptyPlaceException, VehicleNotUsedException{
        this.vehicles.get(i).addVehicle(vehicle);
    }

    /**
     * returns the index of the first available place, -1 if no place available
     * @return int
     */
    public int getFirstAvailablePlace(){
        int res=-1;
        for (StationPlace<E> place : this.vehicles)
            if (place.isAvailable()) return place.getPlaceId();
        return res;
    }


    /**
     * Get all the vehicles of this station
     * @return List<Vehicle>
     */
    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for (StationPlace<E> place : this.vehicles) {
            if (!place.isAvailable()) {
                vehicles.add(place.getVehicle());
            }
        }
        return vehicles;
    }
    
    /**
     * returns the time spent empty for the station
     * @return int
     */
    public int getTimeSpentEmpty() {
    	return this.timeSpentEmpty;
    }
    
    /**
     * increase the time spent empty for this station
     */
    public void increaseTimeSpentEmpty() {
    	this.timeSpentEmpty++;
    }
    
    /**
     * sets the time spent empty to zero for this station
     */
    public void setTimeSpentEmptyToZero() {
    	this.timeSpentEmpty = 0;
    }
    
    /** 
     * increase the time intervals for this station
     */
    public void increaseTime() {
    	if (this.getVehicles().isEmpty())
    		this.increaseTimeSpentEmpty();
    	else 
    		if (this.getTimeSpentEmpty() != 0)
    			this.setTimeSpentEmptyToZero();
    }

}