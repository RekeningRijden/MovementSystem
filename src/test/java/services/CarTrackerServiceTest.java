package services;

import domain.Cartracker;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.CartrackerService;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * Created by martijn on 21-6-2016.
 */
@RunWith(Arquillian.class)
public class CarTrackerServiceTest {

    @Inject
    private CartrackerService cartrackerService;

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
    public void getCartrackerIdsTest() {
        Cartracker cartracker = new Cartracker();
        Cartracker cartracker2 = new Cartracker();

        cartracker.setId(1L);
        cartracker2.setId(2L);

        cartrackerService.create(cartracker);
        cartrackerService.create(cartracker2);

        assertEquals("Size of ids do not match",2,cartrackerService.getAllIds().size());

        assertEquals("Cartracker id does not match",cartracker.getId(),cartrackerService.getAllIds().get(0));
        assertEquals("Cartracker id does not match",cartracker2.getId(),cartrackerService.getAllIds().get(1));
    }

}
