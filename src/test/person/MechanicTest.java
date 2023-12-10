package test.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vlille.vehicles.Vehicle;
import vlille.vehicles.VehicleMock;
import vlille.controlcenter.*;
import vlille.person.*;
import vlille.station.*;
import vlille.controlcenter.*;

class MechanicTest {

    private static VehicleMock v;
    private static Mechanic m;
    private static ControlCenter c;
    private static VehicleStation<Vehicle> s;

    @BeforeEach
    public void init(){
        m = new Mechanic(0);
        s = new VehicleStation<Vehicle>(0, 10);
        c = ControlCenter.getControlCenter();
        c.addStation(s);
        v = new VehicleMock(0, s);
    }

    @Test
    public void testInitGetsGood(){
        assertTrue(v instanceof VehicleMock);
        assertTrue(m instanceof Mechanic);
        assertTrue(c instanceof ControlCenter);
    }
    
    @Test
    public void testGettingRepairingTime() {
    	assertEquals(0, m.getRepairingTime());
    	m.repair(v);
    	m.increaseTime();
    	assertEquals(1, m.getRepairingTime());
    	
    	while (m.getRepairingVehicle() != null) {
    		m.increaseTime();
    	}
    	
    	assertEquals(0, m.getRepairingTime());
    }
    
    
    @Test
    public void testGettingRepairingVehicle() {
    	assertNull(m.getRepairingVehicle());
    	m.repair(v);
    	assertEquals(v, m.getRepairingVehicle());
    	
    	m.increaseTime();
    	while (m.getRepairingTime() != 0) {
    		m.increaseTime();
    	}
    	
    	assertNull(m.getRepairingVehicle());
    }
    
    @Test
    public void testRepairingVehicle(){
    	try {
    		v.use();
    		v.putBack(s);
    	} catch (Exception e) {fail();}
        assertFalse(v.getUsed());
        assertNull(m.getRepairingVehicle());
        m.repair(v);
        assertTrue(v.getUsed());
        assertTrue(m.getRepairingVehicle() instanceof VehicleMock);
        
        for (int i=0; i<m.REPAIRDURATION; i++) m.increaseTime();
        
        assertFalse(v.getUsed());
        assertNull(m.getRepairingVehicle());
    }

}