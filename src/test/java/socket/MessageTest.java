package socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.TrackingPeriod;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.websocket.DecodeException;
import javax.websocket.EncodeException;

import static org.junit.Assert.assertEquals;

/**
 * Created by martijn on 17-6-2016.
 */
@RunWith(Arquillian.class)
public class MessageTest {

    @Inject
    private EndPoint endPoint;

    @Inject
    private MessageDecoder messageDecoder;

    @Inject
    private MessageEncoder messageEncoder;

    @Inject
    private SocketConfigurator socketConfigurator;

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

    @Test
    public void coderTest() throws DecodeException, EncodeException {
        TrackingPeriod trackingPeriod = new TrackingPeriod();

        Message testMessage = new Message(1L,trackingPeriod);

        Gson gson = new GsonBuilder().create();
        Message message = messageDecoder.decode(gson.toJson(testMessage));

        Assert.assertEquals(gson.toJson(testMessage) ,messageEncoder.encode(message));

    }

}
