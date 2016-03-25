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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.bson.Document;

/**
 *
 * @author Marijn
 */
public class TrackingPeriodDaoMongoImp implements TrackingPeriodDao {

    public static final String MONGO_COLLECTION = "trackingperiods";

    private final MongoClient mongoClient = new MongoClient();
    private final MongoDatabase db = mongoClient.getDatabase("s63a");

    private Long getNewSerialNumber(Cartracker ct) {
        FindIterable<Document> iterable = db.getCollection(MONGO_COLLECTION).find(new Document("cartrackerId", ct.getId())).sort(new BasicDBObject("serialNumber" ,-1)).limit(1);
        for (Document document : iterable) {
            return TrackingPeriod.fromDocument(document).getSerialNumber() + 1;
        }
        return Long.valueOf("1");
    }

    @Override
    public TrackingPeriod create(TrackingPeriod tp, Cartracker ct) {
        tp.setSerialNumber(this.getNewSerialNumber(ct));
        Document document = tp.toDocument();
        document.append("cartrackerId", ct.getId());
        /**
         * Insert trackingperiod to database
         */
        db.getCollection(MONGO_COLLECTION).insertOne(document);

        return tp;
    }

    @Override
    public TrackingPeriod findBySerialNumber(Long serialNumber, Cartracker ct) {
        FindIterable<Document> iterable = db.getCollection(MONGO_COLLECTION).find(new Document("cartrackerId", ct.getId()).append("serialNumber", serialNumber));
        for (Document document : iterable) {
            return TrackingPeriod.fromDocument(document);
        }
        return null;
    }

    @Override
    public List<TrackingPeriod> findAll(Cartracker ct) {
        FindIterable<Document> iterable = db.getCollection(MONGO_COLLECTION).find(new Document("cartrackerId", ct.getId()));
        List<TrackingPeriod> trackingPeriods = new ArrayList<>();
        for (Document document : iterable) {
            trackingPeriods.add(TrackingPeriod.fromDocument(document));
        }
        return trackingPeriods;
    }

}
