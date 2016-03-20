/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CartrackerDao;
import dao.CartrackerDaoImp;
import domain.Cartracker;
import java.util.List;

/**
 *
 * @author Eric
 */
public class CartrackerService {
    
    private CartrackerDao cartrackerDao;
    
    public CartrackerService() {
        cartrackerDao = new CartrackerDaoImp();
    }
    
    public Cartracker addNewCartracker(Cartracker cartracker) {
        return cartrackerDao.create(cartracker);
    }
    
    public Cartracker getCartrackerWithId(Long id) {
        return cartrackerDao.findById(id);
    }
    
    public List<Cartracker> getAllCartrackers() {
        return cartrackerDao.findAll();
    }
}
