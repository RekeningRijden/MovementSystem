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
 * @param <T>
 */
public class Pagination {

    private int pageSize = 20;
    private int pageIndex = 0;
    private int totalCount = 0;

    public Pagination() {
        // empty 
    }

    public Pagination(int pageSize, int pageIndex) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    
    
    
}

