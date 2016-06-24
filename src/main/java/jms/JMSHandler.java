package jms;

import domain.Cartracker;
import domain.Position;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.IntegrationService;

/**
 * Created by Eric on 17-06-16.
 */
public class JMSHandler {

    private final IntegrationService integrationService;

    public JMSHandler(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    /**
     * Transforms and routes the messages if needed
     *
     * @param message The received message via the consumer
     * @param fromQueueName The name of the queue where the message came from
     */
    public void handleMessage(String message, String fromQueueName) {
        System.out.println("Message: " + message + ", on queue: " + fromQueueName);

        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(message);
            long cartrackerId = (long) json.get("cartrackerId");
            JSONArray pos = (JSONArray) json.get("positions");
            List<Position> positions = new ArrayList<>();
            Iterator i = pos.iterator();
            while (i.hasNext()) {
                JSONObject position = (JSONObject) i.next();
                Double latitude = (Double) position.get("latitude");
                Double longitude = (Double) position.get("longitude");
                
                Position p = new Position();
                p.setLatitude(latitude);
                p.setLongitude(longitude);
                p.setDate(new Date());
                
                positions.add(p);
            }
            Cartracker cartracker = integrationService.getCartrackerById(cartrackerId);
            if (cartracker == null) {
                cartracker = new Cartracker();
                cartracker.setId(cartrackerId);
                cartracker = integrationService.createCartracker(cartracker);
            }

            integrationService.createTrackingPeriod(cartracker, positions);
        } catch (ParseException ex) {
            Logger.getLogger(JMSHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
