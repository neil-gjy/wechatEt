package com.tjport.common.model;


public class DatatablesSearch {
	
	
	private String value;
	private boolean regex;
	
	public DatatablesSearch(){
		
	}
	
	
	public DatatablesSearch(String value, boolean regex) {
		super();
		this.value = value;
		this.regex = regex;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRegex() {
		return regex;
	}

	public void setRegex(boolean regex) {
		this.regex = regex;
	}

}
