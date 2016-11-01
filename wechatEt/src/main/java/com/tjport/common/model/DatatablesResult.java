package com.tjport.common.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 *
 */
@SuppressWarnings("serial")
public class DatatablesResult<T> implements Serializable {
	

	private int draw; // Client request times
    private long recordsTotal; // Total records number without conditions
    private long recordsFiltered; // Total records number with conditions
    private ArrayList<T> data; //
    
    public DatatablesResult(){
    	
    }
    
    public DatatablesResult(int draw, int recordsTotal, int recordsFiltered, ArrayList<T> data) {

		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public ArrayList<T> getData() {
		return data;
	}

	public void setData(ArrayList<T> data) {
		this.data = data;
	}
    
    
}
