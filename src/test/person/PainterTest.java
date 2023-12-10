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

class PainterTest {

    private static VehicleMock v;
    private static Painter p;
    private static ControlCenter c;
    private static VehicleStation<Vehicle> s;

    @BeforeEach
    public void init(){
        p = new Painter(0);
        s = new VehicleStation<Vehicle>(0, 10);
        c = ControlCenter.getControlCenter();
        c.addStation(s);
        v = new VehicleMock(0, s);
    }

    @Test
    public void testInitGetsGood(){
        assertTrue(v instanceof VehicleMock);
        assertTrue(p instanceof Painter);
        assertTrue(c instanceof ControlCenter);
    }
    
    @Test
    public void testGettingPaintingTime() {
    	assertEquals(0, p.getPaintingTime());
    	p.paint(v);
    	p.increaseTime();
    	assertEquals(1, p.getPaintingTime());
    	
    	while (p.getPaintingVehicle() != null) {
    		p.increaseTime();
    	}
    	
    	assertEquals(0, p.getPaintingTime());
    }
    
    
    @Test
    public void testGettingPaintingVehicle() {
    	assertNull(p.getPaintingVehicle());
    	p.paint(v);
    	assertEquals(v, p.getPaintingVehicle());
    	
    	p.increaseTime();
    	while (p.getPaintingTime() != 0) {
    		p.increaseTime();
    	}
    	
    	assertNull(p.getPaintingVehicle());
    }
    
    @Test
    public void testPaintingVehicle(){
    	try {
    		v.use();
    		v.putBack(s);
    	} catch (Exception e) {fail();}
        assertFalse(v.getUsed());
        assertNull(p.getPaintingVehicle());
        p.paint(v);
        assertTrue(v.getUsed());
        assertTrue(p.getPaintingVehicle() instanceof VehicleMock);
        
        for (int i=0; i<p.PAINTINGDURATION; i++) p.increaseTime();
        
        assertFalse(v.getUsed());
        assertNull(p.getPaintingVehicle());
    }

}