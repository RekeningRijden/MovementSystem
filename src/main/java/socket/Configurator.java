package socket;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Eric on 13-05-16.
 */
public class Configurator extends ServerEndpointConfig.Configurator {

    private static final Logger Log = Logger.getLogger(Configurator.class.getName());
    static {
        Log.setLevel(Level.ALL);
    }

    @Override
    public boolean checkOrigin(String originHeaderValue) {
        Log.log(Level.FINE, "checking origin for {0}", originHeaderValue);
        return true;
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        Log.log(Level.FINE, "modify handshake for {0}, {1}, {2}", new Object[]{sec, request, response});
    }
}
