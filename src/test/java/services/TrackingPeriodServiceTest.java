package services;

import dao.MongoClientProvider;
import dao.TrackingPeriodDao;
import domain.Cartracker;
import domain.TrackingPeriod;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.TrackingPeriodService;
import sun.util.calendar.BaseCalendar;

import javax.inject.Inject;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by martijn on 21-6-2016.
 */
@RunWith(Arquillian.class)
public class TrackingPeriodServiceTest {

    @Inject
    private TrackingPeriodService trackingPeriodService;

    /**
     * @return a jar with all the classes used by Arquillian.
     */
    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive jar = ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "dao", "domain", "org.netbeans.rest.application.config", "resources", "service", "socket")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        System.out.println(jar.toString(true));
        return jar;
    }

    @Before
    public void setUp() {
    }

    @Test
    public void getTrackingPeriodBySerialTest() {
        Cartracker cartracker = new Cartracker();

        TrackingPeriod tp = trackingPeriodService.addTrackingPeriodForCartracker(new TrackingPeriod(),cartracker);
        assertEquals(tp.getId(),trackingPeriodService.getTrackingPeriodBySerialNumber(tp.getSerialNumber(),cartracker).getId());

        TrackingPeriod tpResult = trackingPeriodService.getTrackingPeriodBySerialNumber(tp.getSerialNumber(),cartracker);
        assertEquals(tp.getId(),tpResult.getId());
    }

    @Test
    public void getAllTrackingPeriodsByPeriodTest() throws InterruptedException {
        Date date = new Date();
        Thread.sleep(2000L);
        Date oldDate = new Date();

        Cartracker cartracker = new Cartracker();

        TrackingPeriod tp = new TrackingPeriod();
        tp.setStartedTracking(date);
        tp.setFinishedTracking(oldDate);
        tp.setId(1L);
        tp.setSerialNumber(9L);

        TrackingPeriod tpResult = trackingPeriodService.addTrackingPeriodForCartracker(tp,cartracker);

        List<TrackingPeriod> tps = trackingPeriodService.getAllTrackingPeriodsByPeriod(cartracker,date,oldDate);
        assertEquals("TrackingPeriods list size not correct",1,tps.size());

        assertEquals("TrackingPeriod ID are not equal",(Long)1L,tpResult.getId());
    }

    @Test
    public void getAllTrackingPeriodsForCartrackerTest() {
        Cartracker cartracker = new Cartracker();

        List<TrackingPeriod> tps = trackingPeriodService.getAllTrackingPeriodsFromCartracker(cartracker);

        TrackingPeriod tp = new TrackingPeriod();
        tp.setId(1L);

        TrackingPeriod tp2 = new TrackingPeriod();
        tp2.setId(2L);

        TrackingPeriod tpResult = trackingPeriodService.addTrackingPeriodForCartracker(tp,cartracker);
        assertEquals(tps.size() + 1,trackingPeriodService.getAllTrackingPeriodsFromCartracker(cartracker).size());

        TrackingPeriod tp2Result = trackingPeriodService.addTrackingPeriodForCartracker(tp2,cartracker);
        assertEquals(tps.size() + 2,trackingPeriodService.getAllTrackingPeriodsFromCartracker(cartracker).size());

        tps = trackingPeriodService.getAllTrackingPeriodsFromCartracker(cartracker);
        assertEquals(tps.size(),trackingPeriodService.getAllTrackingPeriodsFromCartracker(cartracker).size());
    }
}
