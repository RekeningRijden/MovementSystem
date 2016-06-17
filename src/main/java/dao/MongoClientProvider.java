package dao;

import com.mongodb.MongoClient;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

/**
 * A Singleton class for maintaining a connection to the MongoDatabase.
 * This Client connection is set up once when the application is deployed.
 *
 * @author Sam
 */
@Singleton
//@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MongoClientProvider implements Serializable {

    private MongoClient mongoClient = null;

    /**
     * @return the MongoClient used to access the MongoDatabase.
     */
    @Lock(LockType.READ)
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    /**
     * Initialize the MongoClient when the application is deployed.
     */
    @PostConstruct
    public void init() {
        mongoClient = new MongoClient("mongo");
    }

    /**
     * Close the MongoClient before the application is shut down.
     */
    @PreDestroy
    private void destroy() {
        mongoClient.close();
    }
}
