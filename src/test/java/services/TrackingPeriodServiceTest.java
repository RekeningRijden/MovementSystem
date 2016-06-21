package services;

import dao.MongoClientProvider;
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

import static org.junit.Assert.assertEquals;

/**
 * Created by martijn on 21-6-2016.
 */
@RunWith(Arquillian.class)
public class TrackingPeriodServiceTest {

    @Inject
    private TrackingPeriodService trackingPeriodService;

//    @Inject
//    private MongoClientProvider mongoClientProvider;

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
    public void getAllTrackingPeriodsTest() {
        Date date = new Date();
        Date oldDate = new Date();

        oldDate.setTime(date.getTime() - 1L);

        Cartracker cartracker = new Cartracker();

        trackingPeriodService.getAllTrackingPeriodsByPeriod(cartracker,date,oldDate);
    }
}
