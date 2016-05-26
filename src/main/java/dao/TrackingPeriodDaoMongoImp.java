/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import domain.Cartracker;
import domain.TrackingPeriod;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.bson.Document;

/**
 * MongoDB implementation of the TrackingPeriodDao
 *
 * @author Marijn
 */
public class TrackingPeriodDaoMongoImp implements TrackingPeriodDao, ServletContextListener {

    public static final String MONGO_COLLECTION = "trackingperiods";
    private static final String COLUMN_CARTRACKERID = "cartrackerId";
    private static final String COLUMN_SERIALNUMBER = "serialNumber";

    private final MongoClient mongoClient;
    private final MongoDatabase db;

    public TrackingPeriodDaoMongoImp() {
        mongoClient = new MongoClient("mongo");
        db = mongoClient.getDatabase("s63a");
    }

    /**
     * Gets a new serialnumber for this cartracker
     *
     * @param ct The cartracker to get a new serialnumber
     * @return The new serialnumber
     */
    private Long getNewSerialNumber(Cartracker ct) {
        FindIterable<Document> iterable = db.getCollection(MONGO_COLLECTION).find(new Document(COLUMN_CARTRACKERID, ct.getId())).sort(new BasicDBObject(COLUMN_SERIALNUMBER, -1)).limit(1);
        for (Document document : iterable) {
            return TrackingPeriod.fromDocument(document).getSerialNumber() + 1;
        }
        return Long.valueOf("1");
    }

    /**
     * Adds a new TrackingPeriod to the MongoDB
     *
     * @param tp The new trackingperiod
     * @param ct The existing cartracker
     * @return The newly added TrackingPeriod
     */
    @Override
    public TrackingPeriod create(TrackingPeriod tp, Cartracker ct) {
        tp.setSerialNumber(this.getNewSerialNumber(ct));
        Document document = tp.toDocument();
        document.append(COLUMN_CARTRACKERID, ct.getId());
        /**
         * Insert trackingperiod to database
         */
        db.getCollection(MONGO_COLLECTION).insertOne(document);

        return tp;
    }

    /**
     * Finds a TrackingPeriod by serialnumber
     *
     * @param serialNumber The serialnumber of the Trackingperiod
     * @param ct The cartracker related to the Trackingperiod
     * @return The TrackingPeriod with the corresponding serialnumber or null
     * when the serial number does not exist for the cartrakcer
     */
    @Override
    public TrackingPeriod findBySerialNumber(Long serialNumber, Cartracker ct) {
        FindIterable<Document> iterable = db.getCollection(MONGO_COLLECTION).find(new Document(COLUMN_CARTRACKERID, ct.getId()).append(COLUMN_SERIALNUMBER, serialNumber));
        for (Document document : iterable) {
            return TrackingPeriod.fromDocument(document);
        }
        return null;
    }

    /**
     * Gets all TrackingPeriods for an existing cartracker
     *
     * @param ct The cartracker containing the TrackingPeriods
     * @return The TrackingPeriods for the cartracker
     */
    @Override
    public List<TrackingPeriod> findAll(Cartracker ct) {
        FindIterable<Document> iterable = db.getCollection(MONGO_COLLECTION).find(new Document(COLUMN_CARTRACKERID, ct.getId()));
        List<TrackingPeriod> trackingPeriods = new ArrayList<>();
        for (Document document : iterable) {
            trackingPeriods.add(TrackingPeriod.fromDocument(document));
        }
        return trackingPeriods;
    }

    /**
     * Get all TrackingPeriods from the specified cartracker in a specific
     * period
     *
     * @param ct The cartracker
     * @param startDate The start date of the TrackingPeriod
     * @param endDate The end date of the TrackingPeriod
     * @return A list of TrackingPeriods from the specified cartracker between
     * the start and end date
     */
    @Override
    public List<TrackingPeriod> findByPeriod(Cartracker ct, Date startDate, Date endDate) {
        Document query = new Document("finishedTracking", new Document("$gte", startDate))
                .append("startedTracking", new Document("$lte", endDate));
        Logger.getLogger("mongo").log(Level.WARNING, query.toJson());
        FindIterable<Document> iterable = db.getCollection(MONGO_COLLECTION).find(query);
        List<TrackingPeriod> trackingPeriods = new ArrayList<>();
        for (Document document : iterable) {
            trackingPeriods.add(TrackingPeriod.fromDocument(document));
        }
        return trackingPeriods;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        mongoClient.close();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // do nothing
    }

}
