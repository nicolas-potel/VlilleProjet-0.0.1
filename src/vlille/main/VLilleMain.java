package vlille.main;

import vlille.controlcenter.*;

public class VLilleMain {

    public static void main(String[] args) throws Exception{
        ControlCenter cc = ControlCenter.getControlCenter(); // Creates the control center
        
        try {
            cc.generateNewStations(3); // Generates all the stations
            cc.generateNewpersons(5); 	// Generates all the persons and professionals
            cc.generateNewVehicles(20); // Generates all the vehicles
        
        } catch (Exception e) {
        	System.out.println("Error during initalisation, please restart.");
        	System.exit(1);
        }
        
        
        for (int i = 1; i<21; i++)
            cc.increaseTime(); // Make the simulation for 20 intervals
    }
    
}