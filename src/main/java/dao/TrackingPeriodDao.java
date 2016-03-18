/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.TrackingPeriod;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author maikel
 */
public abstract class TrackingPeriodDao extends AbstractDao<TrackingPeriod> {

    public List<TrackingPeriod> findByCartrackerId(Long cartrackerId) {
        return getEntityManager().createQuery("SELECT c.movements FROM Cartracker c WHERE c.id = :id", TrackingPeriod.class)
                .setParameter("id", cartrackerId)
                .getResultList();
    }

    //TODO
    public TrackingPeriod findByCartrackerIdAndSerialNumber(Long cartrackerId, Long serialNumber) {
        TypedQuery<TrackingPeriod> q = getEntityManager().createQuery("SELECT t FROM TrackingPeriod t WHERE t.serialNumber = :serialNumber", TrackingPeriod.class)
                .setParameter("id", cartrackerId)
                .setParameter("serialNumber", serialNumber);
        
        return q.getResultList().isEmpty() ? null : q.getResultList().get(0);
    }
}
