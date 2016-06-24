package jms;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import service.IntegrationService;


/**
 * Created by Eric on 17-06-16.
 */

/**
 * Class for setting up everything needed for this webapp
 */
@Singleton
@Startup
public class JMSInit {

    @Inject
    private IntegrationService integrationService;
    private List<JMSConsumer> consumers;

    /**
     * Initiates the needed components for JMS, this method is called at startup
     */
    @PostConstruct
    public void init() {
        createConsumersAndProducers();
    }

    /**
     * Creates the producers and consumers for this app
     */
    public void createConsumersAndProducers() {
        consumers = new ArrayList<>();

        //The producers to communnicate to our other systems
        String[] consumerQueues = {"portugal_foreign_movement_movement"};

        try {

            for (String queue : consumerQueues) {
                JMSConsumer consumer = new JMSConsumer(queue, integrationService);
                consumers.add(consumer);
            }
        } catch (IOException | TimeoutException e) {
            Logger.getLogger(JMSInit.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Closes all open connections when the server shuts down or the app gets
     * undeployed
     */
    @PreDestroy
    public void destroy() {
        for (JMSConsumer consumer : consumers) {
            try {
                consumer.closeConnection();
            } catch (TimeoutException | IOException e) {
                Logger.getLogger(JMSInit.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
