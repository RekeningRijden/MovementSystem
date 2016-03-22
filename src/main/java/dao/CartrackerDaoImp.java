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
        
        Cartracker tracker1 = new Cartracker();
        tracker1.setId(1L);
        tracker1.setAutorisationCode("tracker1");

        Cartracker tracker2 = new Cartracker();
        tracker2.setId(2L);
        tracker2.setAutorisationCode("tracker2");

        Cartracker tracker3 = new Cartracker();
        tracker3.setId(3L);
        tracker3.setAutorisationCode("tracker3");

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
