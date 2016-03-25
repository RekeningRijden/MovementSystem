/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.TrackingPeriodDaoMongoImp;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.bson.Document;

/**
 *
 * @author maikel
 */
public class Position implements Serializable {

    private Long id;
    private Date date;
    private Double longitude;
    private Double latitude;

    public Position() {
    }

    public Position(Long id, Date date, Double longitude, Double latitude) {
        this.id = id;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("date", this.date);
        document.append("longitude", this.longitude);
        document.append("latitude", this.latitude);
        return document;
    }
    
    public static Position fromDocument(Document document) {
        Position position = new Position();
        position.setDate((Date) document.get("date"));
        position.setLongitude((Double) document.get("longitude"));
        position.setLatitude((Double) document.get("latitude"));
        return position;
    }
}
