/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TrackingPeriodDao;
import domain.TrackingPeriod;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author maikel
 */
@Stateless
public class TrackingPeriodService extends TrackingPeriodDao implements Serializable {

    @Override
    protected Class<TrackingPeriod> getEntityClass() {
        return TrackingPeriod.class;
    }
    
}
