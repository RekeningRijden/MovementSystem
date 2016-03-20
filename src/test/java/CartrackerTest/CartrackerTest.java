/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CartrackerTest;

import dao.CartrackerDaoImp;
import domain.Cartracker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.CartrackerService;

/**
 *
 * @author Eric
 */
public class CartrackerTest {
    
    private CartrackerService cartrackerService;
    
    public CartrackerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cartrackerService = new CartrackerService();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void createCartrackerShouldReturnCreatedCartracker() {
        assertEquals(3, cartrackerService.getAllCartrackers().size());
        
        Cartracker cartracker = new Cartracker(4L, "Eric");
        Cartracker createdCartracker = cartrackerService.addNewCartracker(cartracker);
        
        assertEquals((Long)4L, createdCartracker.getId());
        assertEquals("Eric", createdCartracker.getAutorisationCode());
        
        assertEquals(4, cartrackerService.getAllCartrackers().size());
    }
}
