/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagination;

import domain.TrackingPeriod;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marijn
 */
public class TrackingPeriodPagination extends Pagination  {

    private List<TrackingPeriod> items = new ArrayList<>();

    public TrackingPeriodPagination() {
        // empty constructor
    }

    public TrackingPeriodPagination(int pageSize, int pageIndex) {
        super(pageSize, pageIndex);
    }
    
    public List<TrackingPeriod> getItems() {
        return items;
    }

    public void setItems(List<TrackingPeriod> items) {
        this.items = items;
    }
    
    public void addItem(TrackingPeriod item) {
        this.items.add(item);
    }
    
}
