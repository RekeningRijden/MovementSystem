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
     * Adds a new TrackingPeriod for an existing Cartracker.
     *
     * @param trackingPeriod The new trackingperiod
     * @param cartracker The existing cartracker
     * @return The newly added cartracker.
     */
    TrackingPeriod create(TrackingPeriod trackingPeriod, Cartracker cartracker);

    /**
     * Finds a TrackingPeriod based on it's serialnumber.
     *
     * @param serialNumber The serialnumber of the Trackingperiod
     * @param cartracker The cartracker related to the Trackingperiod
     * @return The TrackingPeriod with the corresponding serialnumber.
     */
    TrackingPeriod findBySerialNumber(Long serialNumber, Cartracker cartracker);

    /**
     * Finds all TrackingPeriods for a specific Cartracker.
     *
     * @param cartracker The cartracker containing the TrackingPeriods
     * @return All the TrackingPeriods of the Cartracker.
     */
    List<TrackingPeriod> findAll(Cartracker cartracker);

    /**
     * Find all TrackingPeriods in a specific period.
     *
     * @param ct
     * @param startDate
     * @param endDate
     * @return The TrackingPeriod with the corresponding Period.
     */
    List<TrackingPeriod> findByPeriod(Cartracker ct, Date startDate, Date endDate);
}
