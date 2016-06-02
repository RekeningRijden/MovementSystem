/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagination;

import domain.Cartracker;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marijn
 */
public class CartrackerPaginationTest {

    private CartrackerPagination cartrackerPagination;

    public CartrackerPaginationTest() {
    }

    @Before
    public void setUp() {
        this.cartrackerPagination = new CartrackerPagination();
    }

    @Test
    public void testConstructor() {
        for (int pageSize = 0; pageSize < 100; pageSize++) {
            for (int pageIndex = 0; pageIndex < 100; pageIndex++) {
                CartrackerPagination cartrackerPagination = new CartrackerPagination(pageSize, pageIndex);
                Assert.assertEquals(pageSize, cartrackerPagination.getPageSize());
                Assert.assertEquals(pageIndex, cartrackerPagination.getPageIndex());
            }
        }
    }

    @Test
    public void testItems() {
        Assert.assertEquals(0, this.cartrackerPagination.getItems().size());
        Cartracker cartracker = new Cartracker();
        this.cartrackerPagination.addItem(cartracker);
        Assert.assertEquals(1, this.cartrackerPagination.getItems().size());
        Assert.assertEquals(cartracker, this.cartrackerPagination.getItems().get(0));

        Cartracker cartracker2 = new Cartracker();
        this.cartrackerPagination.addItem(cartracker2);
        Assert.assertEquals(2, this.cartrackerPagination.getItems().size());
        Assert.assertEquals(cartracker, this.cartrackerPagination.getItems().get(0));
        Assert.assertEquals(cartracker2, this.cartrackerPagination.getItems().get(1));

        List<Cartracker> cartrackers = new ArrayList<Cartracker>();
        cartrackers.add(cartracker);
        cartrackers.add(cartracker2);
        this.cartrackerPagination.setItems(cartrackers);
        Assert.assertEquals(2, this.cartrackerPagination.getItems().size());
        Assert.assertEquals(cartracker, this.cartrackerPagination.getItems().get(0));
        Assert.assertEquals(cartracker2, this.cartrackerPagination.getItems().get(1));
    }

}
