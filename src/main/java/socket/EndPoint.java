package socket;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Eric on 13-05-16.
 */
@ServerEndpoint(
        value = "/socket/{pathParam}",
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class},
        configurator = SocketConfigurator.class
)
@Singleton
public class EndPoint {

    private static final Logger LOG = Logger.getLogger(EndPoint.class.getName());

    static {
        LOG.setLevel(Level.ALL);
    }

    private HashMap<Session, Long> usersessions = new HashMap<>();

    @EJB
    private EndPoint delegate;

    @OnOpen
    public void onOpen(Session session, @PathParam("pathParam") String pathParam) {
        LOG.log(Level.FINE, "openend session {0}, pathParam={1}", new Object[]{session, pathParam});
    }

    @OnMessage
    public void onMessage(Session session, Message message) {
        try {
            if (message.getTrackerId() == null && message.getTrackingPeriod() == null && message.getInitTrackerId() != null) {
                //System.out.println("New user added to session with cartrackerId: " + message.getInitTrackerId());
                usersessions.put(session, message.getInitTrackerId());

                //Send confirmation message (for testing)
                session.getBasicRemote().sendObject(new Message(message.getTrackerId(), null));

            } else if (message.getTrackerId() != null && message.getTrackingPeriod() != null && message.getInitTrackerId() == null) {
                //System.out.println("Received trackingPeriod for tracker with id: " + message.getTrackerId());
                for (Map.Entry<Session, Long> entry : usersessions.entrySet()) {
                    if (entry.getValue() == message.getTrackerId()) {
                        //System.out.println("Sending new data");
                        entry.getKey().getBasicRemote().sendObject(message);
                    }
                }
            } else {
                LOG.log(Level.SEVERE, "Something went wrong");
            }
        } catch (Exception ex) {
            LOG.log(Level.WARNING, ex.toString());
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOG.log(Level.SEVERE, "an error occurred in session. Session removed from sessions. " + session, error);
        usersessions.remove(session);
    }

    @OnClose
    public void onClose(Session session) {
        LOG.log(Level.FINE, "closed session {0}", session);
        usersessions.remove(session);
    }
}
