package test.station;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vlille.exceptions.*;
import vlille.vehicles.*;
import vlille.station.*;

class VehicleStationTest {

    private static VehicleStation<VehicleMock> s;
    private static int nbP;
    private static VehicleMock v;

    @BeforeEach
    public void init(){
    	nbP = 10;
        s = new VehicleStation<VehicleMock>(0, nbP);
        v = new VehicleMock(1);
    }

    @Test
    public void testInitGetsGood(){
        assertTrue(v instanceof VehicleMock);
    }

    @Test
    public void testGettingId(){
        assertTrue(s.getId()==0);
    }

    @Test
    public void testGettingNbOfPlaces(){
        assertTrue(s.getNbOfPlaces()==nbP);
    }

    @Test
    public void testAddingVehicleOnPlace(){
        try {
            s.addVehicleAtPlace(0, v);
            assertTrue(s.getVehicleAtPlace(0) instanceof VehicleMock);
            assertFalse(s.isPlaceAvailable(0));
        } catch (Exception e) {fail();}
    }

    @Test
    public void testAddingVehicleOnReservedPlace() throws NotEmptyPlaceException{
    	try {
    		s.addVehicleAtPlace(0, v);
    	} catch (Exception e) {fail();}
        assertThrows(NotEmptyPlaceException.class, () -> {
        	s.addVehicleAtPlace(0, new VehicleMock(2));
        });
    }

    @Test
    public void testRemovingVehicleFromPlace(){
        try {
        	s.addVehicleAtPlace(0, v);
            assertTrue(s.removeVehicleAtPlace(0) instanceof VehicleMock);
            assertTrue(s.isPlaceAvailable(0));
            assertTrue(s.getVehicleAtPlace(0)==null);
        } catch (Exception e) {fail();}
    }

    @Test
    public void testRemovingVehicleOnEmptyPlace() throws EmptyPlaceException{
        assertThrows(EmptyPlaceException.class, () -> {
        	s.removeVehicleAtPlace(0);
        });
    }

    @Test
    public void testGettingFirstAvailablePlace(){
        assertEquals(0, s.getFirstAvailablePlace());
    }
}