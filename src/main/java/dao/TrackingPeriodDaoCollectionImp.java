/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Cartracker;
import domain.Position;
import domain.TrackingPeriod;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test implementation of the TrackingPeriodDao
 *
 * @author Eric
 */
public class TrackingPeriodDaoCollectionImp implements TrackingPeriodDao {

    private HashMap<Long, TrackingPeriod> trackingPeriods;

    public TrackingPeriodDaoCollectionImp() {
        trackingPeriods = new HashMap<>();
        Position pos1 = new Position(1L, new Date(), -8.534009917954325, 37.43534435804771);
        Position pos2 = new Position(2L, new Date(), -8.534009917954325, 38.04134435804773);
        Position pos3 = new Position(3L, new Date(), -8.534009917954325, 38.647344358047754);
        Position pos4 = new Position(4L, new Date(), -8.534009917954325, 38.73734435804776);
        Position pos5 = new Position(5L, new Date(), -8.534009917954325, 38.73734435804776);
        Position pos6 = new Position(6L, new Date(), -8.534009917954325, 38.55734435804775);
        Position pos7 = new Position(7L, new Date(), -8.534009917954325, 37.95134435804773);

        List<Position> positions1 = new ArrayList<>();
        positions1.add(pos1);
        positions1.add(pos2);
        positions1.add(pos3);

        List<Position> positions2 = new ArrayList<>();
        positions2.add(pos4);
        positions2.add(pos5);
        positions2.add(pos6);

        List<Position> positions3 = new ArrayList<>();
        positions3.add(pos7);

        TrackingPeriod tp1 = new TrackingPeriod(1L, 1L, new Date(), new Date(), positions1);
        TrackingPeriod tp2 = new TrackingPeriod(2L, 2L, new Date(), new Date(), positions2);
        TrackingPeriod tp3 = new TrackingPeriod(3L, 3L, new Date(), new Date(), positions3);

        trackingPeriods.put(tp1.getId(), tp1);
        trackingPeriods.put(tp2.getId(), tp2);
        trackingPeriods.put(tp3.getId(), tp3);
    }

    @Override
    public TrackingPeriod create(TrackingPeriod tp, Cartracker ct) {
        tp.setId(trackingPeriods.size() + 1L);
        tp.setSerialNumber(tp.getId());
        trackingPeriods.put(tp.getId(), tp);
        return this.findBySerialNumber(tp.getSerialNumber(), ct);
    }

    @Override
    public TrackingPeriod findBySerialNumber(Long serialNumber, Cartracker ct) {
        return trackingPeriods.get(serialNumber);
    }

    @Override
    public List<TrackingPeriod> findAll(Cartracker ct) {
        List<TrackingPeriod> tempTrackingPeriods = new ArrayList<>();
        for (Map.Entry<Long, TrackingPeriod> entry : trackingPeriods.entrySet()) {
            if (entry.getValue().getId().equals(ct.getId())) {
                tempTrackingPeriods.add(entry.getValue());
            }
        }
        return tempTrackingPeriods;
    }

    @Override
    public List<TrackingPeriod> findByPeriod(Cartracker ct, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
