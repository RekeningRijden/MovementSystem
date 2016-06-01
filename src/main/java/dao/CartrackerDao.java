/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import domain.Cartracker;

/**
 * @author Maikel
 */
public abstract class CartrackerDao extends AbstractDao<Cartracker> {

    /**
     * Retrieve the id's of all the @{CarTrackers}.
     *
     * @return a list of Longs.
     */
    public List<Long> getAllIds() {
        return getEntityManager().createQuery("SELECT c.id FROM Cartracker c", Long.class)
                .getResultList();
    }
}
