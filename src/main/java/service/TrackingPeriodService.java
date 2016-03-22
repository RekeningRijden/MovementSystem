/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TrackingPeriodDao;
import dao.TrackingPeriodDaoImp;
import domain.Cartracker;
import domain.TrackingPeriod;
import java.util.List;

/**
 *
 * @author Eric
 */
public class TrackingPeriodService {
    private TrackingPeriodDao trackingPeriodDao;
    
    public TrackingPeriodService() {
        trackingPeriodDao = new TrackingPeriodDaoImp();
    }
    
    public TrackingPeriod addNewCartracker(TrackingPeriod tp, Cartracker cartracker) {
        return trackingPeriodDao.create(tp, cartracker);
    }
    
    public TrackingPeriod getTrackingPeriodBySerialNumber(Long SerialNumber) {
        return trackingPeriodDao.findBySerialNumber(SerialNumber, null);
    }
    
    public List<TrackingPeriod> getAllTrackingPeriodsFromCartracker(Cartracker cartracker) {
        return trackingPeriodDao.findAll(cartracker);
    }
}
