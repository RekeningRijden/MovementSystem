/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author maikel
 */
@Entity
@Table(name = "Cartracker")
public class Cartracker implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorisationCode;

    @Transient
    private List<TrackingPeriod> movements;

    protected Cartracker() {
        this.authorisationCode = "";
        this.movements = new ArrayList<>();
    }
    
    public Cartracker(String authorisationCode) {
        this();
        this.authorisationCode = authorisationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorisationCode() {
        return authorisationCode;
    }

    public void setAuthorisationCode(String authorisationCode) {
        this.authorisationCode = authorisationCode;
    }

    public List<TrackingPeriod> getMovements() {
        return movements;
    }

    public void setMovements(List<TrackingPeriod> movements) {
        this.movements = movements;
    }
    
    public void addNewTrackingPeriod(TrackingPeriod trackingPeriod) {
        this.movements.add(trackingPeriod);
    }
}
