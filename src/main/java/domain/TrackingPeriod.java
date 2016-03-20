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
/**
 *
 * @author maikel
 */
public class TrackingPeriod implements Serializable{
    
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
}
