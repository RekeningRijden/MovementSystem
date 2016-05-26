package socket;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Eric on 13-05-16.
 */
public class MessageEncoder implements Encoder.Text<Message> {

    private static final Logger LOG = Logger.getLogger(MessageEncoder.class.getName());

    static {
        LOG.setLevel(Level.ALL);
    }

    private final Gson gson = new Gson();

    @Override
    public String encode(Message message) throws EncodeException {
        try {
            return gson.toJson(message);
        } catch (Exception ex) {
            throw new EncodeException(message, "JSON encoding error", ex);
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        LOG.fine("created encoder");
    }

    @Override
    public void destroy() {
        LOG.fine("destroyed encoder");
    }
}
