package socket;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
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

    private static final Logger LOGGER = Logger.getLogger(EndPoint.class.getName());

    static {
        LOGGER.setLevel(Level.ALL);
    }

    private HashMap<Session, Long> usersessions = new HashMap<>();

    @EJB
    private EndPoint delegate;

    @OnOpen
    public void onOpen(Session session, @PathParam("pathParam") String pathParam) {
        LOGGER.log(Level.FINE, "openend session {0}, pathParam={1}", new Object[]{session, pathParam});
    }

    @OnMessage
    public void onMessage(Session session, Message message) {
        try {
            if (message.getTrackerId() == null && message.getTrackingPeriod() == null && message.getInitTrackerId() != null) {
                usersessions.put(session, message.getInitTrackerId());

                session.getBasicRemote().sendObject(new Message(message.getTrackerId(), null));

            } else if (message.getTrackerId() != null && message.getTrackingPeriod() != null && message.getInitTrackerId() == null) {
                sendMessageToUserSessions(mesage);
            } else {
                LOGGER.severe("Something went wrong");
            }
        } catch (Exception ex) {
            LOGGER.warning(ex.toString());
        }
    }

    private void sendMessageToUserSessions(Message message) {
        for (Map.Entry<Session, Long> entry : usersessions.entrySet()) {
            if (entry.getValue() == message.getTrackerId()) {
                entry.getKey().getBasicRemote().sendObject(message);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.severe("an error occurred in session. Session removed from sessions. " + session, error);
        usersessions.remove(session);
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.fine("closed session {0}", session);
        usersessions.remove(session);
    }
}
