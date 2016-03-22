/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

    public Cartracker() {
        this.movements = new ArrayList<>();
    }
    
    public Cartracker(String authorisationCode) {
        this.authorisationCode = generateAuthorisationCode(authorisationCode);
        this.movements = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutorisationCode() {
        return authorisationCode;
    }

    public void setAutorisationCode(String authorisationCode) {
        this.authorisationCode = generateAuthorisationCode(authorisationCode);
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
    
    public String generateAuthorisationCode(String authorisationCode) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(authorisationCode.getBytes());

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
        }
        finally {
            return "";
        }
    }
    
    public void saveAuthorisationCodeFile() {
        try {
            byte data[] = getAutorisationCode().getBytes();
            Path file = Paths.get("authcodetestfile");
            Files.write(file, data);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    public String toString() {
        return this.getAutorisationCode();
    }
    
}
