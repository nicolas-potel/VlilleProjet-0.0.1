package test.vehicles;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vlille.exceptions.VehicleAlreadyUsedException;
import vlille.exceptions.VehicleNotUsedException;
import vlille.station.VehicleStation;
import vlille.vehicles.feature.FeatureMock;
import vlille.vehicles.VehicleMock;
import vlille.vehicles.Vehicle;

class VehicleTest {

	private static VehicleMock v;
	private static VehicleStation<Vehicle> station;
	private static FeatureMock feature;
	
	@BeforeEach
	public void init() {
		station = new VehicleStation<Vehicle>(0, 10);
		v = new VehicleMock(0, station);
	}
	
	@Test
	public void testGettingVehicleId() {
		assertTrue(v.getId()==0);
	}
	
	@Test
	public void testAssertingVehicleUsed() {
		// The vehicle is not available
		assertFalse(v.getUsed());
	}
	
	@Test
	public void testVehicleGettingStolen() {
		// Vehicle is currently not stolen
		assertFalse(v.getStolen());
		
		// Setting the vehicle stolen
		v.setStolen(true);
		
		// The vehicle is now stolen
		assertTrue(v.getStolen());
	}

	@Test
	public void getColorWhereNull() {
		assertTrue(v.getColor() == null);
	}
	
	@Test
	public void setColorAndGetColorWhereNotNull() {
		assertTrue(v.getColor() == null);
		
		v.setColor(Color.red);
		
		assertTrue(v.getColor() == Color.red);		
	}
	
	@Test
	public void testGetStation() {
		// The vehicle is not in a station, so its stationId equals -1
		assertEquals(station, v.getStation());
	}
	
    @Test
    public void testUsingVehicleThatIsNotAvailable() throws VehicleAlreadyUsedException{
        v.use();
    	assertThrows(VehicleAlreadyUsedException.class, () -> {
    		v.use();
        });
    }
	
	@Test
	public void testPuttingVehicleOnPlace() throws VehicleNotUsedException{
		try {
			v.use();
		} catch(VehicleAlreadyUsedException e) {fail();}
		v.putBack(station);
		
		// Assert vehicle is correctly placed and not used
        assertFalse(v.getUsed());
        assertEquals(v.getStation(), station);
	}

    @Test
    public void testUsingVehicleAndGettingNbUses() throws VehicleAlreadyUsedException, VehicleNotUsedException{
    	assertTrue(v.getNbUses() == 0);
    	v.use();
    	assertTrue(v.getNbUses() == 1);
    	
    	//Putting the vehicle on a place before trying to use it
    	v.putBack(station);
    	
    	
    	v.use();
		
		assertTrue(v.getNbUses()==2);
		assertTrue(v.getUsed());
    }

	
	@Test
	public void testAddingFeature() {
		feature = new FeatureMock(v);
		
		assertTrue(v.getFeatures().contains(feature));
	}

    // Tests to be added

    // printFeatures






}
