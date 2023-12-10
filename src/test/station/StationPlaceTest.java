package test.station;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vlille.exceptions.*;
import vlille.vehicles.*;
import vlille.station.*;

public class StationPlaceTest {

    private static VehicleMock v;
    private static StationPlace<VehicleMock> p;

    @BeforeEach
    public void init(){
        v = new VehicleMock(0);
        p = new StationPlace<VehicleMock>(5,0); // place of id 0 in station of id 5
    }

    @Test
    public void testInitGetsGood(){
        assertTrue(v instanceof VehicleMock);
        assertTrue(p instanceof StationPlace); 
    }

    @Test
    public void testGettingStationIdFromPlace(){
        assertTrue(p.getStationId()==5);
    }

    @Test
    public void testGettingPlaceNumber(){
        assertTrue(p.getPlaceId()==0);
    }

    @Test
    public void testGettingVehicleFromEmptyPlace(){
        assertNull(p.getVehicle());
    }

    @Test
    public void testAddingVehicleToPlace() {
        assertTrue(p.isAvailable());
        try {
        	p.addVehicle(v);
        } catch (Exception e) {fail();}
        assertFalse(p.isAvailable());
    }

    @Test
    public void testAddingVehicleOnReservedPlace() throws VehicleNotUsedException, NotEmptyPlaceException{
        try{
        	p.addVehicle(v);
        } catch (Exception e) {fail();}
        assertThrows(NotEmptyPlaceException.class, () -> {
        	p.addVehicle(new VehicleMock(1));    	
        });
    }

    @Test
    public void testGettingVehicleFromPlace(){
    	try {
    		p.addVehicle(v);
    	} catch (Exception e) {fail();}
        assertTrue(p.getVehicle() instanceof VehicleMock);
    }

    @Test
    public void testRemovingVehicleFromPlace() throws EmptyPlaceException{
        try {
        	p.addVehicle(v);
        } catch (Exception e) {fail();}
        p.removeVehicle();
        assertTrue(p.isAvailable());
    }

    @Test
    public void testRemovingVehicleFromEmptyPlace() throws EmptyPlaceException{
    	assertThrows(EmptyPlaceException.class, () -> {
    		p.removeVehicle();
    	});
    }


}