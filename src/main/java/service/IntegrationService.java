/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Cartracker;
import domain.Position;
import domain.TrackingPeriod;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author maikel
 */
@Named
public class IntegrationService {

    @Inject
    private CartrackerService cartrackerService;
    @Inject
    private TrackingPeriodService trackingPeriodService;

    public Cartracker getCartrackerById(long cartrackerId) {
        return cartrackerService.findById(cartrackerId);
    }

    public Cartracker createCartracker(Cartracker cartracker) {
        return cartrackerService.update(cartracker);
    }
    
    public void createTrackingPeriod(Cartracker cartracker, List<Position> positions){
        TrackingPeriod period = new TrackingPeriod();
        period.setPositions(positions);
        period.setStartedTracking(new Date());
        trackingPeriodService.addTrackingPeriodForCartracker(period, cartracker);
    }

}
