/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.Cartracker;
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
        cartracker.setAuthorisationCode("hoi");
        assertEquals("hoi", cartracker.getAuthorisationCode());
    }

    @Test
    public void testConstructer() {
        Cartracker cartracker = new Cartracker("hoi");
        assertEquals("hoi", cartracker.getAuthorisationCode());
    }
}