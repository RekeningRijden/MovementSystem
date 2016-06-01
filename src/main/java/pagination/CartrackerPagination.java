/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagination;

import domain.Cartracker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marijn
 */
public class CartrackerPagination extends Pagination  {

    private List<Cartracker> items = new ArrayList<>();

    public CartrackerPagination() {
        // empty constructor
    }

    public CartrackerPagination(int pageSize, int pageIndex) {
        super(pageSize, pageIndex);
    }
    
    public List<Cartracker> getItems() {
        return items;
    }

    public void setItems(List<Cartracker> items) {
        this.items = items;
    }
    
    public void addItem(Cartracker item) {
        this.items.add(item);
    }
    
}
