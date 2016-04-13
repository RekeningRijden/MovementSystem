/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;
import junit.framework.Assert;
import static org.junit.Assert.assertSame;
import org.junit.Test;

/**
 *
 * @author Marijn
 */
public class PositionTest {
    
    @Test
    public void testGettersAndSetters() {
        Position position = new Position();

        /* Id */
        position.setId(Long.parseLong("1"));
        assertSame(Long.parseLong("1"), position.getId());
        
        /* Date */
        Date date = new Date();
        position.setDate(date);
        assertSame(date, position.getDate());
        
        /* Longitude */
        position.setLongitude(1.0);
        Assert.assertEquals(1.0, position.getLongitude());
        
        /* Latitude */
        position.setLatitude(2.0);
        Assert.assertEquals(2.0, position.getLatitude());
    }
    
    @Test
    public void testConstructor() {
        Date date = new Date();
        Position position = new Position(Long.parseLong("1"), date, 2.0, 3.0);
        assertSame(Long.parseLong("1"), position.getId());
        assertSame(date, position.getDate());
        Assert.assertEquals(date, position.getDate());
        Assert.assertEquals(2.0, position.getLongitude());
        Assert.assertEquals(3.0, position.getLatitude());
    }
}
