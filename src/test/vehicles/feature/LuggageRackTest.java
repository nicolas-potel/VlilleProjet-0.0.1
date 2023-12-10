package test.vehicles.feature;

import org.junit.jupiter.api.BeforeAll;

import vlille.vehicles.*;
import vlille.vehicles.feature.*;

public class LuggageRackTest {

	private static VehicleMock v;
    private static LuggageRack b;

    @BeforeAll
    public static void init(){
        v = new VehicleMock(0);
        b = new LuggageRack(v);
    }
    
}