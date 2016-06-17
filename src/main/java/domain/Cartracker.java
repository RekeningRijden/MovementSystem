/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Maikel
 */
@Entity
@Table(name = "Cartracker")
@NamedQueries({
    @NamedQuery(name = "Cartracker.count", query = "SELECT COUNT(1) FROM Cartracker c") 
})
public class Cartracker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorisationCode;

    @Transient
    private List<TrackingPeriod> trackingPeriods;

    public Cartracker() {
        this.authorisationCode = generateAuthorisationCode();
    }

    public Cartracker(String authorisationCode) {
        this();
        this.authorisationCode = authorisationCode;
    }

    private String generateAuthorisationCode() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(UUID.randomUUID().toString().getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cartracker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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

    public List<TrackingPeriod> getTrackingPeriods() {
        return trackingPeriods;
    }

    public void setTrackingPeriods(List<TrackingPeriod> trackingPeriods) {
        this.trackingPeriods = trackingPeriods;
    }

    public void addTrackingPeriod(TrackingPeriod trackingPeriod) {
        this.trackingPeriods.add(trackingPeriod);
    }
}
