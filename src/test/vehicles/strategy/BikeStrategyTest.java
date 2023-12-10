package test.vehicles.strategy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import vlille.vehicles.Bike;
import vlille.vehicles.strategy.BikeStrategy;

public class BikeStrategyTest {

	private static Bike v;
	
	@BeforeAll
	public static void init() {
		v = new Bike(0);
	}
	
}
