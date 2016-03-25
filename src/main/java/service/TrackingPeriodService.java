/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TrackingPeriodDao;
import dao.TrackingPeriodDaoCollectionImp;
import dao.TrackingPeriodDaoMongoImp;
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
        trackingPeriodDao = new TrackingPeriodDaoMongoImp();
    }
    
    public TrackingPeriod addNewCartracker(TrackingPeriod tp, Cartracker cartracker) {
        return trackingPeriodDao.create(tp, cartracker);
    }
    
    public TrackingPeriod getTrackingPeriodBySerialNumber(Long SerialNumber, Cartracker cartracker) {
        return trackingPeriodDao.findBySerialNumber(SerialNumber, cartracker);
    }
    
    public List<TrackingPeriod> getAllTrackingPeriodsFromCartracker(Cartracker cartracker) {
        return trackingPeriodDao.findAll(cartracker);
    }
}
