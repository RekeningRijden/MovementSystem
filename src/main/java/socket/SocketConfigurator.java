package socket;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Eric on 13-05-16.
 */
public class SocketConfigurator extends ServerEndpointConfig.Configurator {

    private static final Logger LOG = Logger.getLogger(SocketConfigurator.class.getName());

    static {
        LOG.setLevel(Level.ALL);
    }

    @Override
    public boolean checkOrigin(String originHeaderValue) {
        LOG.log(Level.FINE, "checking origin for {0}", originHeaderValue);
        return true;
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        LOG.log(Level.FINE, "modify handshake for {0}, {1}, {2}", new Object[]{sec, request, response});
    }
}
