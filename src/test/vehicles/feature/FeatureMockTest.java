package test.vehicles.feature;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import vlille.vehicles.*;
import vlille.vehicles.feature.*;

public class FeatureMockTest {

	private static VehicleMock v;
    private static FeatureMock b;

    @BeforeAll
    public static void init(){
        v = new VehicleMock(0);
        b = new FeatureMock(v);
    }

    @Test
    public void testInitGetsGood(){
    	assertTrue(v instanceof VehicleMock);
        assertTrue(b instanceof FeatureMock);
    }
    
    @Test
    public void testCheckingBasketIsInFeatureList() {
    	assertTrue(v.getFeatures().contains(b));
    }
    
}