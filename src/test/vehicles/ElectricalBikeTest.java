package test.vehicles;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vlille.vehicles.strategy.*;
import vlille.vehicles.*;

public class ElectricalBikeTest {

    private static ElectricalBike v;

    @BeforeEach
    public void init(){
        v = new ElectricalBike(0);
    }

    @Test
    public void testInitIsRight(){
        assertTrue(v instanceof ElectricalBike);
    }

    @Test
    public void testDischargeBattery() {
        assertEquals(100, v.getBatteryCharge());
        v.dischargeBattery();
        assertEquals(100-v.getChargeSpeed(), v.getBatteryCharge());
        
        while (v.getBatteryCharge() > 0) {
        	v.dischargeBattery();
        }
        assertTrue(v.getBatteryCharge() == 0);
    }

    @Test
    public void testChargeBattery() {
    	// Battery charge percent is at 95/ due to previous tests
        assertEquals(100, v.getBatteryCharge());
        v.dischargeBattery();
        
        v.chargeBattery();
        assertEquals(100, v.getBatteryCharge());
        
        
    }
}