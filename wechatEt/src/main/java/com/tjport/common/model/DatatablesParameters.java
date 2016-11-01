package com.tjport.common.model;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;

public class DatatablesParameters {
	
	private int draw;
	private int start;
	private int length;
	private ArrayList<DatatablesColumn> columns = new  ArrayList<DatatablesColumn>();
	private ArrayList<DatatablesOrder> order;
	private String search;
	private String id;
	
	DatatablesParameters(){
		
	}

	

	
	public DatatablesParameters(int draw, int start, int length, ArrayList<DatatablesColumn> columns,
			ArrayList<DatatablesOrder> order, String search, String id) {

		this.draw = draw;
		this.start = start;
		this.length = length;
		this.columns = columns;
		this.order = order;
		this.search = search;
		this.id = id;
	}




	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@JSONField
	public ArrayList<DatatablesColumn> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<DatatablesColumn> columns) {
		this.columns = columns;
	}

	@JSONField
	public ArrayList<DatatablesOrder> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<DatatablesOrder> order) {
		this.order = order;
	}

	@JSONField
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getOrderBy() {
		if (this.columns != null && this.columns.size() > 0 && this.order != null && this.order.size() > 0) {
			return this.columns.get(this.order.get(0).getColumn()).getData();
		}
		else {
			return "";
		}
	}
	
	public String getOrderDir() {
		if ( this.order != null && this.order.size() > 0) {
			return this.order.get(0).getDir();
		}
		else {
			return "desc";
		}
	}




	



	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}
}
