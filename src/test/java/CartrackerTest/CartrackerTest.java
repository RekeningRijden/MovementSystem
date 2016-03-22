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
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import domain.Cartracker;
import domain.Position;
import domain.TrackingPeriod;
import resources.CartrackerResource;
import service.CartrackerService;

import static org.junit.Assert.assertEquals;

/**
 * @author Eric
 */
@RunWith(Arquillian.class)
public class CartrackerTest {

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

    @Test
    public void createCartrackerTest() {
        Position pos1 = new Position(1L, new Date(), -8.534009917954325, 37.43534435804771);
        Position pos2 = new Position(2L, new Date(), -8.534009917954325, 38.04134435804773);
        Position pos3 = new Position(3L, new Date(), -8.534009917954325, 38.647344358047754);

        List<Position> positions = new ArrayList<>();
        positions.add(pos1);
        positions.add(pos2);
        positions.add(pos3);

        TrackingPeriod trackingPeriod = new TrackingPeriod(1L, 1L, new Date(), new Date(), positions);

        Cartracker tracker = new Cartracker();
        tracker.setId(1L);
        tracker.setAutorisationCode("tracker1");
        tracker.addNewTrackingPeriod(trackingPeriod);

        cartrackerResource.addNewTracker(tracker);

        assertEquals("Wrong amount of cartrackers in the database", 1, cartrackerResource.getTrackers().size());
    }

    @Test
    public void addTrackingPeriodTest(){

    }
}
