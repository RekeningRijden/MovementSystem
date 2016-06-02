/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marijn
 */
public class TrackingPeriodTest {

    public TrackingPeriodTest() {
    }

    @Test
    public void testGettersAndSetters() {
        TrackingPeriod trackingPeriod = new TrackingPeriod();

        /* Id */
        trackingPeriod.setId(Long.parseLong("1"));
        assertSame(Long.parseLong("1"), trackingPeriod.getId());

        /* StartedDate */
        Date date = new Date();
        trackingPeriod.setStartedTracking(date);
        assertSame(date, trackingPeriod.getStartedTracking());

        /* FinishedDate */
        date = new Date();
        trackingPeriod.setFinishedTracking(date);
        assertSame(date, trackingPeriod.getFinishedTracking());

        /* SerialNumber */
        trackingPeriod.setSerialNumber(Long.parseLong("5"));
        assertSame(Long.parseLong("5"), trackingPeriod.getSerialNumber());

        /* Positions */
        List<Position> positions = new ArrayList<>();
        trackingPeriod.setPositions(positions);
        assertSame(positions, trackingPeriod.getPositions());
    }

    @Test
    public void testConstructor() {
        Date startDate = new Date();
        Date endDate = new Date();
        TrackingPeriod trackingPeriod = new TrackingPeriod(Long.parseLong("1"), Long.parseLong("5"), startDate, endDate, null);
        assertSame(Long.parseLong("1"), trackingPeriod.getId());
        assertSame(startDate, trackingPeriod.getStartedTracking());
        assertSame(endDate, trackingPeriod.getFinishedTracking());
        assertSame(Long.parseLong("5"), trackingPeriod.getSerialNumber());
    }

    @Test
    public void testToAndFromDocument() {
        Date startDate = new Date();
        Date endDate = new Date();
        TrackingPeriod trackingPeriod = new TrackingPeriod(Long.parseLong("10"), Long.parseLong("15"), startDate, endDate, new ArrayList<Position>());
        TrackingPeriod trackingPeriodConverted = TrackingPeriod.fromDocument(trackingPeriod.toDocument());
        assertSame(startDate, trackingPeriodConverted.getStartedTracking());
        assertSame(endDate, trackingPeriodConverted.getFinishedTracking());
        assertSame(Long.parseLong("15"), trackingPeriodConverted.getSerialNumber());
    }
}
