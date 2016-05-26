package socket;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Eric on 13-05-16.
 */
public class MessageDecoder implements Decoder.Text<Message> {

    private final Gson gson = new Gson();

    private static final Logger LOG = Logger.getLogger(MessageDecoder.class.getName());
    static {
        LOG.setLevel(Level.ALL);
    }

    @Override
    public Message decode(String message) throws DecodeException{
        try {
            return gson.fromJson(message, Message.class);
        }
        catch (Exception ex) {
            throw new DecodeException(message, "JSON decoding error", ex);
        }
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        LOG.fine("created decoder");
    }

    @Override
    public void destroy() {
        LOG.fine("destroyed decoder");
    }
}
