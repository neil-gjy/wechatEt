package com.tjport.common.model;

public class DatatablesColumn {
	
	

	private String data;
	private String name;
	private boolean orderable;
	
	public DatatablesColumn(){
		
	}
	
	

	public DatatablesColumn(String data, String name, boolean orderable) {
		this.data = data;
		this.name = name;
		this.orderable = orderable;
	}



	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public boolean isOrderable() {
		return orderable;
	}

	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}


	
}
