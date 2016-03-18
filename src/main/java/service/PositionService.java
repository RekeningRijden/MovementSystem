/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PositionDao;
import domain.Position;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author maikel
 */
@Stateless
public class PositionService extends PositionDao implements Serializable {

    @Override
    protected Class<Position> getEntityClass() {
        return Position.class;
    }
    
}
