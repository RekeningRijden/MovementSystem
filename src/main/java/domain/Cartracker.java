/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maikel
 */
public class Cartracker implements Serializable{
    
    private Long id;
    private String autorisationCode;
    private List<TrackingPeriod> movements;

    public Cartracker() {
        this.movements = new ArrayList<>();
    }
    
    public Cartracker(Long id, String authorisatieCode) {
        this.id = id;
        this.autorisationCode = authorisatieCode;
        this.movements = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutorisationCode() {
        return autorisationCode;
    }

    public void setAutorisationCode(String autorisationCode) {
        this.autorisationCode = autorisationCode;
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
    
    @Override
    public String toString() {
        return this.getAutorisationCode();
    }
    
}
