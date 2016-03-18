package com.mycompany.movementsystem;

import javax.inject.Inject;
import service.CarTrackerService;
import service.PositionService;
import service.TrackingPeriodService;

/**
 *
 * @author maikel
 */
public class MovementSystem {
    
    @Inject
    private CarTrackerService carTrackerService;
    @Inject
    private TrackingPeriodService trackingPeriodService;
    @Inject
    private PositionService positionService;
    
    public String testMethod(){
        return "test";
    }
}
