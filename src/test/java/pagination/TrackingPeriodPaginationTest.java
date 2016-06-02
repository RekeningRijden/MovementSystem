/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagination;

import domain.TrackingPeriod;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Marijn
 */
public class TrackingPeriodPaginationTest {

    private TrackingPeriodPagination trackingPeriodPagination = new TrackingPeriodPagination();

    public TrackingPeriodPaginationTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testConstructor() {
        for (int pageSize = 0; pageSize < 100; pageSize++) {
            for (int pageIndex = 0; pageIndex < 100; pageIndex++) {
                TrackingPeriodPagination trackingPeriodPagination = new TrackingPeriodPagination(pageSize, pageIndex);
                Assert.assertEquals(pageSize, trackingPeriodPagination.getPageSize());
                Assert.assertEquals(pageIndex, trackingPeriodPagination.getPageIndex());
            }
        }
    }

    @Test
    public void testItems() {
        Assert.assertEquals(0, this.trackingPeriodPagination.getItems().size());
        TrackingPeriod trackingPeriod = new TrackingPeriod();
        this.trackingPeriodPagination.addItem(trackingPeriod);
        Assert.assertEquals(1, this.trackingPeriodPagination.getItems().size());
        Assert.assertEquals(trackingPeriod, this.trackingPeriodPagination.getItems().get(0));

        TrackingPeriod trackingPeriod2 = new TrackingPeriod();
        this.trackingPeriodPagination.addItem(trackingPeriod2);
        Assert.assertEquals(2, this.trackingPeriodPagination.getItems().size());
        Assert.assertEquals(trackingPeriod, this.trackingPeriodPagination.getItems().get(0));
        Assert.assertEquals(trackingPeriod2, this.trackingPeriodPagination.getItems().get(1));

        List<TrackingPeriod> trackingPeriods = new ArrayList<TrackingPeriod>();
        trackingPeriods.add(trackingPeriod);
        trackingPeriods.add(trackingPeriod2);
        this.trackingPeriodPagination.setItems(trackingPeriods);
        Assert.assertEquals(2, this.trackingPeriodPagination.getItems().size());
        Assert.assertEquals(trackingPeriod, this.trackingPeriodPagination.getItems().get(0));
        Assert.assertEquals(trackingPeriod2, this.trackingPeriodPagination.getItems().get(1));
    }

}
