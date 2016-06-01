/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Maikel
 */
public class TrackingPeriod implements Serializable {

    private Long id;
    private Long serialNumber;
    private Date startedTracking;
    private Date finishedTracking;
    private List<Position> positions;

    public TrackingPeriod() {
        positions = new ArrayList();
    }

    public TrackingPeriod(Long id, Long serialNumber, Date startedTracking, Date finishedTracking, List<Position> positions) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.startedTracking = startedTracking;
        this.finishedTracking = finishedTracking;
        this.positions = positions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getStartedTracking() {
        return startedTracking;
    }

    public void setStartedTracking(Date startedTracking) {
        this.startedTracking = startedTracking;
    }

    public Date getFinishedTracking() {
        return finishedTracking;
    }

    public void setFinishedTracking(Date finishedTracking) {
        this.finishedTracking = finishedTracking;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public void addPosition(Position position) {
        this.positions.add(position);
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("serialNumber", this.serialNumber);
        document.append("startedTracking", this.startedTracking);
        document.append("finishedTracking", this.finishedTracking);
        List<Document> positionsList = new ArrayList<>();
        for (Position position : this.positions) {
            positionsList.add(position.toDocument());
        }
        document.append("positions", positionsList);
        return document;
    }

    public static TrackingPeriod fromDocument(Document document) {
        TrackingPeriod trackingPeriod = new TrackingPeriod();
        trackingPeriod.setSerialNumber((Long) document.get("serialNumber"));
        trackingPeriod.setStartedTracking((Date) document.get("startedTracking"));
        trackingPeriod.setFinishedTracking((Date) document.get("finishedTracking"));
        List<Document> positionsAsList = (List<Document>) document.get("positions");
        for (Document positionsDoc : positionsAsList) {
            trackingPeriod.addPosition(Position.fromDocument(positionsDoc));
        }
        return trackingPeriod;
    }
}
