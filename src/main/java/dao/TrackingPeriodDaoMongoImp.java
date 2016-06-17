package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import domain.Cartracker;
import domain.TrackingPeriod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.ManyToOne;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.bson.Document;

/**
 * MongoDB implementation of the TrackingPeriodDao.
 *
 * @author Marijn
 */
@Stateless
public class TrackingPeriodDaoMongoImp implements Serializable {

    public static final String MONGO_COLLECTION = "trackingperiods";
    private static final String COLUMN_CARTRACKERID = "cartrackerId";
    private static final String COLUMN_SERIALNUMBER = "serialNumber";

    @EJB
    private MongoClientProvider mongoClientProvider;

    private MongoDatabase getMongoDb() {
        return mongoClientProvider.getMongoClient().getDatabase("s63a");
    }

    /**
     * Gets a new serialnumber for this cartracker.
     *
     * @param ct The cartracker to get a new serialnumber
     * @return The new serialnumber.
     */
    private Long getNewSerialNumber(Cartracker ct) {
        FindIterable<Document> iterable = getMongoDb().getCollection(MONGO_COLLECTION).find(new Document(COLUMN_CARTRACKERID, ct.getId())).sort(new BasicDBObject(COLUMN_SERIALNUMBER, -1)).limit(1);
        for (Document document : iterable) {
            return TrackingPeriod.fromDocument(document).getSerialNumber() + 1;
        }
        return Long.valueOf("1");
    }

    /**
     * Adds a new TrackingPeriod to the MongoDB.
     *
     * @param tp The new trackingperiod
     * @param ct The existing cartracker
     * @return The newly added TrackingPeriod.
     */
    public TrackingPeriod create(TrackingPeriod tp, Cartracker ct) {
        tp.setSerialNumber(this.getNewSerialNumber(ct));
        Document document = tp.toDocument();
        document.append(COLUMN_CARTRACKERID, ct.getId());
        /**
         * Insert trackingperiod to database.
         */
        getMongoDb().getCollection(MONGO_COLLECTION).insertOne(document);

        return tp;
    }

    /**
     * Finds a TrackingPeriod by serialnumber.
     *
     * @param serialNumber The serialnumber of the Trackingperiod
     * @param ct           The cartracker related to the Trackingperiod
     * @return The TrackingPeriod with the corresponding serialnumber or null
     * when the serial number does not exist for the cartracker.
     */
    public TrackingPeriod findBySerialNumber(Long serialNumber, Cartracker ct) {
        FindIterable<Document> iterable = getMongoDb().getCollection(MONGO_COLLECTION).find(new Document(COLUMN_CARTRACKERID, ct.getId()).append(COLUMN_SERIALNUMBER, serialNumber));
        for (Document document : iterable) {
            return TrackingPeriod.fromDocument(document);
        }
        return null;
    }

    /**
     * Gets all TrackingPeriods for an existing cartracker.
     *
     * @param ct The cartracker containing the TrackingPeriods
     * @return The TrackingPeriods for the cartracker.
     */
    public List<TrackingPeriod> findAll(Cartracker ct) {
        // to prevent code duplication 
        return this.findAllPaginated(ct, 0, 9999999);
    }

    /**
     * Get all TrackingPeriods from the specified cartracker in a specific
     * period.
     *
     * @param cartracker        The cartracker
     * @param startDate The start date of the TrackingPeriod
     * @param endDate   The end date of the TrackingPeriod
     * @return A list of TrackingPeriods from the specified cartracker between
     * the start and end date.
     */
    public List<TrackingPeriod> findByPeriod(Cartracker cartracker, Date startDate, Date endDate) {
        Document query = new Document("finishedTracking", new Document("$gte", startDate))
                .append("startedTracking", new Document("$lte", endDate)).append(COLUMN_CARTRACKERID, cartracker.getId());
        Logger.getLogger("mongo").log(Level.WARNING, query.toJson());
        FindIterable<Document> iterable = getMongoDb().getCollection(MONGO_COLLECTION).find(query);
        List<TrackingPeriod> trackingPeriods = new ArrayList<>();
        for (Document document : iterable) {
            trackingPeriods.add(TrackingPeriod.fromDocument(document));
        }
        return trackingPeriods;
    }

    public List<TrackingPeriod> findAllPaginated(Cartracker cartracker, int pageIndex, int pageSize) {
        FindIterable<Document> iterable = getMongoDb().getCollection(MONGO_COLLECTION).find(new Document(COLUMN_CARTRACKERID, cartracker.getId()));
        iterable.skip(pageIndex * pageSize);
        iterable.limit(pageSize);
        List<TrackingPeriod> trackingPeriods = new ArrayList<>();
        for (Document document : iterable) {
            trackingPeriods.add(TrackingPeriod.fromDocument(document));
        }
        return trackingPeriods;
    }

    public int countAll(Cartracker cartracker) {
        return (int) getMongoDb().getCollection(MONGO_COLLECTION).count(new Document(COLUMN_CARTRACKERID, cartracker.getId()));
    }
}
