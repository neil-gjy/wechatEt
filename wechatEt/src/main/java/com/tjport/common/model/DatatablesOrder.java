package com.tjport.common.model;

public class DatatablesOrder {
	
	
	private int column;
	private String dir;
	
	public DatatablesOrder(){
		
	}

	public DatatablesOrder(int column, String dir) {
		super();
		this.column = column;
		this.dir = dir;
	}
	
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

}
