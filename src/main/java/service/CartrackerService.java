package service;

import java.io.Serializable;

import javax.ejb.Stateless;

import dao.CartrackerDao;
import domain.Cartracker;

/**
 * @author Maikel
 */
@Stateless
public class CartrackerService extends CartrackerDao implements Serializable {

    @Override
    protected Class<Cartracker> getEntityClass() {
        return Cartracker.class;
    }
}
