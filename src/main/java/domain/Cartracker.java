/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

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
    private String autorisationCode;
    private List<TrackingPeriod> movements;

    public Cartracker() {
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
    
}
