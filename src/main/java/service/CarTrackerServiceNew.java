/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CartrackerDaoNew;
import domain.Cartracker;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author maikel
 */
@Stateless
public class CarTrackerServiceNew extends CartrackerDaoNew implements Serializable {

    @Override
    protected Class<Cartracker> getEntityClass() {
        return Cartracker.class;
    }
    
}
