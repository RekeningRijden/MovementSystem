/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author maikel
 */
@Entity
@Table(name = "TrackingPeriod")
public class TrackingPeriod implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long serialNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedTracking;
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedTracking;
    private List<Position> positions;

    public TrackingPeriod() {
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
