package vlille.controlcenter;

import java.util.*;

import vlille.station.*;   
import vlille.vehicles.*;
import vlille.vehicles.feature.*;
import vlille.person.*;
import vlille.exceptions.*;


public class ControlCenter {

    /* Create only one control center */
    public static ControlCenter CONTROL;

    /* The real time of an interval */
    public static final int INTERVALTIME = 1000;

    /* the number of intervals a station can spend empty before needing a redistribution */
    public static final int TIMEEMPTYBEFOREREDISTRIBUTION = 2;
    
    /* The current number of interval */
    private static int nbIntervals;

    /* All stations managed by this control center */
    private static List<VehicleStation<Vehicle>> stations;

    /* All the vehicle classes name to be used */
    private static final List<String> vehicleClasses = new ArrayList<String>();
    
    /* All the persons managed by the control center */
    private List<Person> persons;
    
    /* All the vehicles managed by the control center */
    private List<Vehicle> vehicles;

    /* Random attribute to be used to make random actions */
    private Random alea;


    /** The constructor for ControlCenter */
    private ControlCenter() {
        nbIntervals = 1;
        this.persons = new ArrayList<Person>();
        stations = new ArrayList<VehicleStation<Vehicle>>();
        this.vehicles = new ArrayList<Vehicle>();
        this.alea = new Random();
        
        vehicleClasses.add("Bike");
        vehicleClasses.add("ElectricalBike");
        
    }
    
    public static ControlCenter getControlCenter() {
    	if (CONTROL == null)
    		CONTROL = new ControlCenter();
    	return CONTROL;
    }

    
    /** --------Getter-------- */

    /**
     * Get the time spent since last interval
     * @return int
     */
    public int getIntervalNumber() {
        return nbIntervals;  
    }
    
    /**
     * return the list of persons managed by the control center
     * @return List<Person>
     */
    public List<Person> getPersons(){
    	return this.persons;
    }
    
    /**
     * return a list of persons corresponding to the given job
     * @param job String representing a job class name
     * @return List<Person>
     */
    public List<Person> getPersonsByJob(String job){
    	List<Person> res = new ArrayList<Person>();
    	Class<?> jobClass;
    	
    	try {
			jobClass = Class.forName("vlille.person." + job);
		} catch (ClassNotFoundException e) {
			System.out.println("Error : the " + job + " job does not exists.");
			return res;
		}
    	
    	for (Person p : this.getPersons())
    		if (jobClass.isInstance(p)) res.add(p);

    	  	
    	return res;
    }

    /**
     * Get all stations managed by this control center
     * @return List<VehicleStation<Vehicle>>
     */
    public static List<VehicleStation<Vehicle>> getStations() {
        return stations;
    }

    /**
     * Get Vehicles managed by this control center
     * @return List<Vehicle>
     */
    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }
    
    /**
     * Get a person which is available corresponding to the given job
     * return null if none available
     * @param job String corresponding to the job class name
     * @return Person
     */
    protected Person getAvailablePerson(String job) {
		Person p = null;
			
	    List<Person> users = this.getPersonsByJob(job);
	    int usersSize = users.size();
		
		for (int i = 0; i<usersSize && p==null; i++)
			if ( users.get(i).isAvailable() )
				p = users.get(i);
		
		return p;
	}

    /**
     * return an available Vehicle, null if no vehicle available
     * @return Vehicle
     */
	protected Vehicle getAvailableVehicle() {
		int index = 0;
		int vehiclesSize = this.vehicles.size();
		Vehicle vehicleToBeUsed = null;
		
     
		// Getting the first not used vehicle
		while (vehicleToBeUsed == null && index < vehiclesSize) {
			
			Vehicle tempVehicle = this.vehicles.get(index);
			if (!tempVehicle.getUsed())
				vehicleToBeUsed = tempVehicle;
			index++;
		}
		return vehicleToBeUsed;
	}
	
	/**
	 * Get the list of used (or not) vehicles
	 * @param used true if you want all the used vehicle
	 * @return List<Vehicle>
	 */
	protected List<Vehicle> getUsedVehicles(boolean used){
		List<Vehicle> res = new ArrayList<Vehicle>();
		
		for (Vehicle v : this.vehicles)
			if (v.getUsed() == used) res.add(v);
				
		return res;
	}


    /** --------Other Functions------- */
	
	/**
	 * Add the station to the station list
	 * @param station the station to add
	 */
	public void addStation(VehicleStation<Vehicle> station) {
		ControlCenter.getStations().add(station);
	}
	
	/**
     * Generates n new stations
     * @param n the number of stations to generate
     */
    public void generateNewStations(int n) {
        for (int i=0; i<n; i++) {
        	
        	int nbOfPlaces = alea.nextInt(VehicleStation.PLACESUPPERLIMIT - VehicleStation.PLACESLOWERLIMIT)
        			+ VehicleStation.PLACESLOWERLIMIT;
        	int index = getStations().size();
        	addStation(new VehicleStation<Vehicle>(index, nbOfPlaces));      	
        	
        }
        System.out.println(n + " vehicle stations has been created.");
    }
    
    /** 
     * Generates n new vehicles
     * @param n the number of vehicles to generate
     */
    public void generateNewVehicles(int n) throws VehicleNotUsedException{
        for (int i = 0; i < n ; i++) {

            Vehicle v;
            VehicleStation<Vehicle> s;
            
            int tempStationIndex = this.alea.nextInt(getStations().size());
            if (tempStationIndex < 0) {
            	System.out.println("Error generating vehicles : there is not station in the controlcenter.");
            	System.exit(-1);
            }
            s = getStations().get(tempStationIndex);

            if (this.alea.nextInt(2) == 0) v = new Bike(i, s);
            else v = new ElectricalBike(i, s);    
            this.vehicles.add(v);
            
            int randomFeature = alea.nextInt(10)+1;
            if (randomFeature % 2 == 0)
            	new Basket(v);
            if (randomFeature % 3 == 0)
            	new Foldable(v);
            if (randomFeature % 5 == 0)
            	new LuggageRack(v);
            
            if (v.getFeatures().isEmpty())
            	System.out.println("The " + v + " has been created with no feature.");
            else {
            	System.out.println("The " + v + " has been created with the feature(s) : " + v.printFeatures());
            }

        }     
        
        System.out.println(n+" vehicles has been generated.");
    }
    
    /**
     * Generates n new persons 
     * @param n the number of persons to create
     */
    public void generateNewpersons(int n) {
    	int id = this.getPersons().size();
        for (int i=0; i<n; i++) {
        	
        	this.getPersons().add(new Person(id));
        	id++;
        	
        	this.getPersons().add(new Mechanic(id));
        	id++;
        	
        	this.getPersons().add(new Painter(id));
        	id++;  	
        	
        }
        System.out.println(n + " persons of each job has been created.");

    }
  
    /**
     * Make random vehicle uses and deposits
     */
    public void randomVehiclesUsing() {
       
    	int numberOfVehiclesToUse = alea.nextInt(this.getVehicles().size())+1;
        int nbOfVehiclesUsed = 0;
    	
    	
    	for (int i=0; i<numberOfVehiclesToUse; i++) {
    	
	        Vehicle vehicleToBeUsed = null;
	        Person personWhoseGonnaUseVehicle = null;
	        
			vehicleToBeUsed = getAvailableVehicle();	        
	        personWhoseGonnaUseVehicle = getAvailablePerson("Person");
	        
	        
	        if (personWhoseGonnaUseVehicle != null && vehicleToBeUsed != null)
	        	try {
	        		personWhoseGonnaUseVehicle.takeVehicle(vehicleToBeUsed);
	        		nbOfVehiclesUsed++;
	        	} catch (VehicleAlreadyUsedException e) { System.out.println("Error : the vehicle is already used."); }  
    	}
    	if (nbOfVehiclesUsed == 0 && numberOfVehiclesToUse != 0)
    		System.out.println("No vehicle and person available.");

    }

    
    

    /**
     * Put back the vehicle of the person in the station corresponding to the id
     * @param person the person whose got to deposit its vehicle
     */
    public void putBackPersonsVehicle(Person person) {
    	person.giveBackVehicle();
    }
    
    /**
     * Make repairs on vehicle which needs it
     * affects only unused vehicles
     */
    private void makeRepairsOnVehicles() {
    	
    	for (Vehicle v : this.getUsedVehicles(false)) {
    		Mechanic m = (Mechanic) this.getAvailablePerson("Mechanic");
			if (m == null) {
				System.out.println("No mechanic currently available.");
				break;
			}
			
    		if (v.getNbOfUsesSinceLastRepair() >= v.NBUSESBEFOREREPAIR)
    			m.repair(v);
    		
    		
    	}
    }
    
    /**
     * Make random painting on unused vehicles
     */
    private void makeRandomPainting() {
    	int nbOfPainting = alea.nextInt(this.getUsedVehicles(false).size()+1);
    	
    	for (Vehicle v : this.getUsedVehicles(false)) {
    		if (nbOfPainting > 0) {
	    		Painter m = (Painter) this.getAvailablePerson("Painter");
				if (m == null) {
					System.out.println("No painter currently available.");
					break;
				}
	    		m.paint(v);
	    		nbOfPainting--;
    		}
    	}
    }
    
    /**
     * returns true if the vehicles need a redistribution
     * @return
     */
    private boolean needRedistribution() {
    	boolean res = false;
    	for (VehicleStation<Vehicle> s : getStations())
    		if (s.getTimeSpentEmpty() == TIMEEMPTYBEFOREREDISTRIBUTION) {
    			System.out.println("The station "+ s.getId() + " has been empty too much time.");
    			return true;  
    		}
    	return res;
    }
    
    /**
     * Reorganizes all the vehicles in the stations
     */
    private void makeRedistribution() {
    	System.out.println("Proceding to vehicles redistribution.");
    	List<Vehicle> vehicles = this.getUsedVehicles(false);
    	List<VehicleStation<Vehicle>> stations = getStations();
    	int index = 0;
    	
    	for (Vehicle v : vehicles) {
    		if (index >= stations.size())
    			index = 0;
    		try {
    			v.use();
				v.putBack(
						stations.get(index)
						);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    		index++;
    	}
    	
    }
    


    /** 
     * Increase the time by one interval
     */
    public void increaseTime() {
        
    	System.out.println("\n-----=== Interval "+ nbIntervals +" ===-----");
    	nbIntervals++;
    	
        for (Vehicle v : this.getVehicles()) v.increaseTime();
        for (Person p : this.getPersons()) p.increaseTime();
        for (VehicleStation<Vehicle> s : getStations()) s.increaseTime();
        
        randomVehiclesUsing();
        makeRepairsOnVehicles();
        makeRandomPainting();
        if (needRedistribution()) 
        	makeRedistribution();

        try {
            Thread.sleep(INTERVALTIME);
        } catch (Exception e) {}
    }

}