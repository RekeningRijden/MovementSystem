package CartrackerTest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import domain.Cartracker;
import domain.Position;
import domain.TrackingPeriod;
import resources.CartrackerResource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * @author Eric
 */
@RunWith(Arquillian.class)
public class CarTrackerResourceTest {

    @Inject
    private CartrackerResource cartrackerResource;

    /**
     * @return a jar with all the classes used by Arquillian.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "dao", "domain", "org.netbeans.rest.application.config", "resources", "service")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private Cartracker buildCartracker(String authorisationCode) {
        Position pos1 = new Position(1L, new Date(), -8.534009917954325, 37.43534435804771);
        Position pos2 = new Position(2L, new Date(), -8.534009917954325, 38.04134435804773);
        Position pos3 = new Position(3L, new Date(), -8.534009917954325, 38.647344358047754);
        Position pos4 = new Position(4L, new Date(), -8.534009917954325, 38.73734435804776);
        Position pos5 = new Position(5L, new Date(), -8.534009917954325, 38.73734435804776);
        Position pos6 = new Position(6L, new Date(), -8.534009917954325, 38.55734435804775);

        List<Position> positions = new ArrayList<>();
        positions.add(pos1);
        positions.add(pos2);
        positions.add(pos3);

        List<Position> positions2 = new ArrayList<>();
        positions2.add(pos4);
        positions2.add(pos5);
        positions2.add(pos6);

        TrackingPeriod trackingPeriod = new TrackingPeriod(1L, 1L, new Date(), new Date(), positions);
        TrackingPeriod trackingPeriod2 = new TrackingPeriod(2L, 2L, new Date(), new Date(), positions2);

        Cartracker cartracker = new Cartracker();
        cartracker.setAutorisationCode(authorisationCode);
        cartracker.addNewTrackingPeriod(trackingPeriod);
        cartracker.addNewTrackingPeriod(trackingPeriod2);

        return cartracker;
    }

    @Test
    public void getCartrackerByIdTest() {
        //Build a CarTracker and add it to the database
        Cartracker cartracker = buildCartracker("tracker2");
        cartrackerResource.addNewTracker(cartracker);

        //Get the CarTracker by built carTracker id
        Cartracker result = cartrackerResource.getCartrackerById(cartracker.getId());

        assertSame("Not the correct CarTracker retrieved", cartracker, result);
    }

    @Test
    public void getAllTrackersTest() {
        //Build all CarTracker and add them to the database
        Cartracker carTracker = new Cartracker();
        carTracker.setAutorisationCode("tracker4");

        Cartracker carTracker2 = new Cartracker();
        carTracker2.setAutorisationCode("tracker5");

        Cartracker carTracker3 = new Cartracker();
        carTracker3.setAutorisationCode("tracker6");

        //Get all the carTrackers in the database
        List<Cartracker> carTrackers = cartrackerResource.getAllTrackers();

        assertEquals("Wrong amount of CarTrackers received", 3, carTrackers.size());
        assertEquals("Wrong first CarTracker received", carTracker.getId(), carTrackers.get(0).getId());
        assertEquals("Wrong second CarTracker received", carTracker2.getId(), carTrackers.get(1).getId());
        assertEquals("Wrong third CarTracker received", carTracker3.getId(), carTrackers.get(2).getId());
    }

    @Test
    public void createCartrackerTest() {
        //Build a CarTracker and add it to the database
        Cartracker cartracker = buildCartracker("tracker1");
        cartrackerResource.addNewTracker(cartracker);

        assertEquals("Wrong amount of CarTracker in the database", 1, cartrackerResource.getAllTrackers().size());
    }



    @Test
    public void getTrackingPeriodsFromCartrackerByIdTest() {
        //Build a CarTracker and add it to the database
        Cartracker cartracker = buildCartracker("tracker3");
        cartrackerResource.addNewTracker(cartracker);

        //Get the trackingPeriods by built carTracker id
        List<TrackingPeriod> trackingPeriods = cartrackerResource.getMovementsFromCartrackerWithId(cartracker.getId());

        assertEquals("Wrong amount of trackingPeriods received", 2, trackingPeriods.size());
        assertEquals("Wrong first trackingPeriods received", cartracker.getMovements().get(0).getSerialNumber(), trackingPeriods.get(0).getSerialNumber());
        assertEquals("Wrong second trackingPeriods received", cartracker.getMovements().get(1).getSerialNumber(), trackingPeriods.get(1).getSerialNumber());
    }


    @Test
    public void addTrackingPeriodTest() {
        //Build a CarTracker and add it to the database
        Cartracker cartracker = buildCartracker("tracker3");
        cartrackerResource.addNewTracker(cartracker);

        //Build a trackingPeriod with a few positions
        Position pos1 = new Position(1L, new Date(), -8.534009917954325, 37.43534435804771);
        Position pos2 = new Position(2L, new Date(), -8.534009917954325, 38.04134435804773);
        Position pos3 = new Position(3L, new Date(), -8.534009917954325, 38.647344358047754);

        List<Position> positions = new ArrayList<>();
        positions.add(pos1);
        positions.add(pos2);
        positions.add(pos3);

        TrackingPeriod trackingPeriod = new TrackingPeriod(1L, 1L, new Date(), new Date(), positions);

        //cartrackerResource.addTrackingPeriodToCarTracker(trackingPeriod, cartracker);

        Cartracker result = cartrackerResource.getCartrackerById(cartracker.getId());

        assertEquals("TrackingPeriod not added to the CarTracker", 1, result.getMovements().size());
        assertEquals("Wrong trackingPeriod added to the CarTracker", trackingPeriod.getSerialNumber(), result.getMovements().get(0).getSerialNumber());
    }
}
