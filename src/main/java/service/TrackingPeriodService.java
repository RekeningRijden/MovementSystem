/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.TrackingPeriodDaoMongoImp;
import domain.Cartracker;
import domain.TrackingPeriod;

/**
 * @author Eric
 */
@Stateless
public class TrackingPeriodService implements Serializable {

    @Inject
    private TrackingPeriodDaoMongoImp trackingPeriodDao;

    /**
     * Adds a TrackingPeriod for the specified cartracker.
     *
     * @param tp         The new TrackingPeriod
     * @param cartracker The cartracker containing the TrackingPeriod
     * @return The newly added TrackingPeriod.
     */
    public TrackingPeriod addTrackingPeriodForCartracker(TrackingPeriod tp, Cartracker cartracker) {
        return trackingPeriodDao.create(tp, cartracker);
    }

    /**
     * Gets a TrackingPeriod with the specified serialnumber and cartracker.
     *
     * @param SerialNumber The serialnumber of the TrackingPeriod
     * @param cartracker   The cartracker containing the TrackingPeriod
     * @return The TrackingPeriod with specified serialnumber.
     */
    public TrackingPeriod getTrackingPeriodBySerialNumber(Long serialNumber, Cartracker cartracker) {
        return trackingPeriodDao.findBySerialNumber(serialNumber, cartracker);
    }

    /**
     * Gets all TrackingPeriod from the specified cartracker.
     *
     * @param cartracker The cartracker containing the TrackingPeriods
     * @return All TrackingPeriods from the specified cartracker.
     */
    public List<TrackingPeriod> getAllTrackingPeriodsFromCartracker(Cartracker cartracker) {
        return null; //rackingPeriodDao.findAll(cartracker);
    }

    /**
     * Count all TrackingPeriod from the specified cartracker
     *
     * @param cartracker The cartracker containing the TrackingPeriods
     * @return All TrackingPeriods from the specified cartracker
     */
    public int countAllTrackingPeriodsFromCartracker(Cartracker cartracker) {
        return trackingPeriodDao.countAll(cartracker);
    }

    /**
     * Gets all TrackingPeriod paginated from the specified cartracker
     *
     * @param cartracker
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<TrackingPeriod> getAllTrackingPeriodsFromCartrackerPaginated(Cartracker cartracker, int pageIndex, int pageSize) {
        return trackingPeriodDao.findAllPaginated(cartracker, pageIndex, pageSize);
    }

    /**
     * Get all TrackingPeriods from the specified cartracker in a specific
     * period.
     *
     * @param cartracker The cartracker
     * @param startDate  The start date of the TrackingPeriod
     * @param endDate    The end date of the TrackingPeriod
     * @return A list of TrackingPeriods from the specified cartracker between
     * the start and end date.
     */
    public List<TrackingPeriod> getAllTrackingPeriodsByPeriod(Cartracker cartracker, Date startDate, Date endDate) {
        return trackingPeriodDao.findByPeriod(cartracker, startDate, endDate);
    }
}
