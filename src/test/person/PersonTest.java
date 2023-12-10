package test.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vlille.exceptions.VehicleAlreadyUsedException;
import vlille.person.*;
import vlille.vehicles.Vehicle;
import vlille.vehicles.VehicleMock;
import vlille.station.*;
import vlille.controlcenter.*;

class PersonTest {
	
	private Person p;
	private VehicleMock v;
	private VehicleStation<Vehicle> s;
	private ControlCenter c;
	
	@BeforeEach
	public void init() {
		p = new Person(0);
		s = new VehicleStation<Vehicle>(0, 10);
		v = new VehicleMock(0, s);
		c = ControlCenter.getControlCenter();
		c.addStation(s);
	}
	
	@Test
	public void testInitWentGood() {
		assertTrue(p instanceof Person);
	}

	@Test
	void testGettingPersonId() {
		assertEquals(0, p.getId());
	}
	
	@Test
	void testGettingAvailability() {
		assertTrue(p.isAvailable());
	}

	
	@Test
	void testGettingVehicle() {
		assertNull(p.getVehicle());
	}
	
	@Test
	void testTakeAVehicle() {
		assertNull(p.getVehicle());
		try {
			p.takeVehicle(v);
		} catch(VehicleAlreadyUsedException e) {fail();}
		assertTrue(p.getVehicle() instanceof VehicleMock);
	}
	
	
	@Test
	void testGiveBackVehicle() {
		try {
			p.takeVehicle(v);
		} catch(VehicleAlreadyUsedException e) {fail();}
		assertTrue(p.getVehicle() instanceof VehicleMock);
		p.giveBackVehicle();
		assertNull(p.getVehicle());
	}
	
	@Test
	void testIncreaseTime() {
		try {
			p.takeVehicle(v);
		} catch(VehicleAlreadyUsedException e) {fail();}
		
		while (!p.isAvailable()) {
			p.increaseTime();
		}
		
		assertTrue(p.isAvailable());
		assertNull(p.getVehicle());
	}
	
	
}
