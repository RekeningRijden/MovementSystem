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

/**
 *
 * @author Eric
 */
public class CartrackerDaoImp implements CartrackerDao {

    private HashMap<Long, Cartracker> trackers;

    public CartrackerDaoImp() {
        trackers = new HashMap<>();
        
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
        
        Cartracker tracker1 = new Cartracker();
        tracker1.setId(1L);
        tracker1.setAutorisationCode("tracker1");
        tracker1.addNewTrackingPeriod(tp1);

        Cartracker tracker2 = new Cartracker();
        tracker2.setId(2L);
        tracker2.setAutorisationCode("tracker2");
        tracker2.addNewTrackingPeriod(tp2);

        Cartracker tracker3 = new Cartracker();
        tracker3.setId(3L);
        tracker3.setAutorisationCode("tracker3");
        tracker3.addNewTrackingPeriod(tp3);

        trackers.put(tracker1.getId(), tracker1);
        trackers.put(tracker2.getId(), tracker2);
        trackers.put(tracker3.getId(), tracker3);
    }

    @Override
    public Cartracker create(Cartracker cartracker) {
        trackers.put(cartracker.getId(), cartracker);

        return this.findById(cartracker.getId());
    }

    @Override
    public Cartracker findById(Long id) {
        return trackers.get(id);
    }

    @Override
    public List<Cartracker> findAll() {
        return new ArrayList(trackers.values());
    }

}
