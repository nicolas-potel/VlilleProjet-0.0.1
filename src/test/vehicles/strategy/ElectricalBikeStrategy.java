package test.vehicles.strategy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import vlille.exceptions.VehicleNotUsedException;
import vlille.vehicles.ElectricalBike;

public class ElectricalBikeStrategy {

	private static ElectricalBike v;
	
	@BeforeAll
	public static void init() {
		v = new ElectricalBike(0);
	}
}