/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Cartracker;
import domain.TrackingPeriod;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface TrackingPeriodDao {
    
    TrackingPeriod create(TrackingPeriod tp, Cartracker ct);
    
    TrackingPeriod findBySerialNumber(Long serialNumber, Cartracker ct);
    
    List<TrackingPeriod> findAll(Cartracker ct);
    
    //GettrackingPeriodWithinPeriod
    
}
