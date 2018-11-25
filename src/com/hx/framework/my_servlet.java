package com.hx.framework;

public class my_servlet {
    String id;
    String clazz;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * @param id
	 * @param clazz
	 */
	public my_servlet(String id, String clazz) {
		super();
		this.id = id;
		this.clazz = clazz;
	}
	/**
	 * 
	 */
	public my_servlet() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
