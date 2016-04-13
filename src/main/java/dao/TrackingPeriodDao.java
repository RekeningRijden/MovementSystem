/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Cartracker;
import domain.TrackingPeriod;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface TrackingPeriodDao {

    /**
     * Adds a new TrackingPeriod for an existing Cartracker
     * @param tp The new trackingperiod
     * @param ct The existing cartracker
     * @return The newly added cartracker
     */
    TrackingPeriod create(TrackingPeriod tp, Cartracker ct);

    /**
     * Finds a TrackingPeriod based on it's serialnumber
     * @param serialNumber The serialnumber of the Trackingperiod
     * @param ct The cartracker related to the Trackingperiod
     * @return The TrackingPeriod with the corresponding serialnumber
     */
    TrackingPeriod findBySerialNumber(Long serialNumber, Cartracker ct);

    /**
     * Finds all TrackingPeriods for a specific Cartracker
     * @param ct The cartracker containing the TrackingPeriods
     * @return All the TrackingPeriods of the Cartracker
     */
    List<TrackingPeriod> findAll(Cartracker ct);
    
    /**
     * Find all TrackingPeriods in a specific period.
     * @param ct
     * @param startDate
     * @param endDate
     * @return 
     */
    List<TrackingPeriod> findByPeriod(Cartracker ct, Date startDate, Date endDate);
}
