package test.vehicles;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import vlille.exceptions.VehicleAlreadyUsedException;
import vlille.exceptions.VehicleNotUsedException;
import vlille.station.VehicleStation;
import vlille.vehicles.*;

public class BikeTest {

    private static Bike b;
    private static VehicleStation<Vehicle> station;
	
	@BeforeAll
	public static void init() {
		station = new VehicleStation<Vehicle>(0,station.PLACESUPPERLIMIT);
		b = new Bike(0, station);
	}

    @Test
    public void testInitIsRight() {
		assertTrue(b instanceof Bike);
        assertEquals(0, b.getId());
	}

    @Test
    public void testGetAndSetTimeSpentAlone() {
        assertEquals(0, b.getTimeSpentAlone());
        b.setTimeSpentAlone(1);
        assertEquals(1, b.getTimeSpentAlone());
    }
    
    @Test
    public void testResetTimeSpentAloneWhenGettingUsed() throws VehicleNotUsedException, VehicleAlreadyUsedException{
    	b.use();
    	b.putBack(station);
    	b.use();
    	assertEquals(b.getTimeSpentAlone(), 0);
    }

}
