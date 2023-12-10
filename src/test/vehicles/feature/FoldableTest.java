package test.vehicles.feature;

import org.junit.jupiter.api.BeforeAll;

import vlille.vehicles.*;
import vlille.vehicles.feature.*;

public class FoldableTest {

	private static VehicleMock v;
    private static Foldable b;

    @BeforeAll
    public static void init(){
        v = new VehicleMock(0);
        b = new Foldable(v);
    }
    
}