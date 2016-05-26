package service;

/**
 * Created by Sam on 22-3-2016.
 */
import java.io.Serializable;

import javax.ejb.Stateless;

import dao.CartrackerDao;
import domain.Cartracker;

/**
 * @author maikel
 */
@Stateless
public class CartrackerService extends CartrackerDao implements Serializable {

    @Override
    protected Class<Cartracker> getEntityClass() {
        return Cartracker.class;
    }
}
