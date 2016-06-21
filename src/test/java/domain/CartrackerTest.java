/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.Cartracker;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marijn
 */
public class CartrackerTest {

    @Test
    public void testGettersAndSetters() {
        Cartracker cartracker = new Cartracker();

        /* Id */
        cartracker.setId(Long.parseLong("1"));
        assertSame(Long.parseLong("1"), cartracker.getId());
        
        /* Authorisationcode */
        cartracker.setAuthorisationCode("testCode");
        assertEquals("testCode", cartracker.getAuthorisationCode());
        
        /* Trackingperiods */
        List<TrackingPeriod> trackingPeriods = new ArrayList<>();
        cartracker.setTrackingPeriods(trackingPeriods);
        assertSame(trackingPeriods, cartracker.getTrackingPeriods());
        cartracker.addTrackingPeriod(new TrackingPeriod());
        assertSame(1, cartracker.getTrackingPeriods().size());
    }

    @Test
    public void testConstructer() {
        Cartracker cartracker = new Cartracker("testCode");
        cartracker.setId(1L);
        assertEquals("testCode", cartracker.getAuthorisationCode());

        Cartracker cartracker2 = new Cartracker("testCode2");
        cartracker2.setId(2L);
        assertEquals("testCode2", cartracker2.getAuthorisationCode());

        assertNotSame("testCode", cartracker2.getAuthorisationCode());
        assertNotSame("testCode2", cartracker.getAuthorisationCode());

        assertEquals((Long)1L,cartracker.getId());
        assertEquals((Long)2L,cartracker2.getId());
    }
    
    
}
