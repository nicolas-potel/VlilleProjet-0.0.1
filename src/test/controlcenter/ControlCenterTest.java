package test.controlcenter;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import vlille.controlcenter.ControlCenter;

public class ControlCenterTest {

    private ControlCenter c;

    @Before
    private void init(){
        this.c = ControlCenter.getControlCenter();
    }


    @Test
    private void testInitGetsGood(){
        assertTrue(this.c instanceof ControlCenter);
        
        assertTrue(ControlCenter.CONTROL instanceof ControlCenter);
    }
}
