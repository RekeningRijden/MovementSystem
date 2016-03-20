/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Cartracker;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface CartrackerDao {
    
    Cartracker create(Cartracker cartracker);
    
    Cartracker findById(Long id);
    
    List<Cartracker> findAll();
}
